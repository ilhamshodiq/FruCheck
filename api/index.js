const express = require('express');
const Multer = require('multer');
const fs = require('fs');
const { Storage } = require('@google-cloud/storage');
const admin = require('firebase-admin');

const dotenv = require('dotenv');
dotenv.config();

// Initialize Express application
const app = express();

//konfigurasi multer menggunakan gcloud
const multerStorage = Multer.memoryStorage();
const upload = Multer({ storage: multerStorage, });

// Initialize Firebase Admin
const serviceAccount = require('./serviceAccountKey.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: process.env.FIREBASE_DATABASE_URL,
  storageBucket: serviceAccount.storageBucket,
});

// Initialize Google Cloud Storage
const storage = new Storage();
const bucketName = 'history-user';
const bucket = storage.bucket(bucketName);

// Initialize Firestore
const db = admin.firestore();

// API endpoint for image scanning
app.post('/scan', upload.single('image'), (req, res) => {
    // Memeriksa apakah ada file gambar yang diunggah
    if (!req.file) {
      return res.status(400).json({ message: 'No image file uploaded' });
    }
  
    const imageBuffer = req.file.buffer; // Mengambil buffer file gambar
  
    // Menentukan nama file yang akan disimpan di Cloud Storage
    const fileName = `${Date.now()}_${req.file.originalname}`;
    const file = bucket.file(fileName);
  
    // Membuat stream file dan mengunggah buffer gambar ke Cloud Storage
    const stream = file.createWriteStream({
      metadata: {
        contentType: req.file.mimetype,
      },
    });
  
    stream.on('error', (error) => {
      console.error('Error uploading image:', error);
      res.status(500).json({ message: 'Failed to upload image' });
    });
  
    stream.on('finish', () => {
      // Proses machine learning untuk menganalisis gambar
      // Ganti dengan kode pemrosesan gambar menggunakan model machine learning
  
      // Menghapus file yang diunggah setelah diproses
      req.file.buffer = null;
  
      // Menampilkan output hasil pemrosesan
      const output = 'Output dari model machine learning';
      res.json({ output });
    });
  
    stream.end(imageBuffer);
  });
  

// API endpoint to fetch fruit data by ID
// API endpoint to fetch all fruit data
app.get('/buah', (req, res) => {
  // Retrieve all fruit documents from Firestore collection
  db.collection('daftar_buah')
    .get()
    .then((snapshot) => {
      if (snapshot.empty) {
        // No fruit documents found
        res.status(404).json({ message: 'Tidak ada buah yang ditemukan' });
        return;
      }

      // Extract the fruit data from the snapshot
      const fruits = [];
      snapshot.forEach((doc) => {
        const buahData = doc.data();
        fruits.push(buahData);
      });

      // Send the fruit data as a response
      res.json({ 
        message : 'Data buah berhasil diambil',
        error : false,
        buah: fruits });
    })
    .catch((error) => {
      console.error('Error while retrieving fruit data:', error);
      res.status(500).json({ message: 'Terjadi kesalahan saat mengambil data buah' });
    });
});


// Start the application server
const port = process.env.PORT || 8080;
app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});