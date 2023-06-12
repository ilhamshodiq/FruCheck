const express = require('express');
const Multer = require('multer');
const fs = require('fs');
const { Storage } = require('@google-cloud/storage');
const admin = require('firebase-admin');

const dotenv = require('dotenv');
dotenv.config();

// Initialize Express application
const app = express();
const upload = Multer({ dest: 'uploads/' });

// Initialize Firebase Admin
const serviceAccount = require('./serviceAccountKey.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: process.env.FIREBASE_DATABASE_URL,
});

// Initialize Google Cloud Storage
const storage = new Storage();
const bucket = storage.bucket('history-user');

// Initialize Firestore
const db = admin.firestore();

// API endpoint for image scanning
app.post('/scan', upload.single('image'), (req, res) => {
  // Check if an image file is uploaded
  if (!req.file) {
    return res.status(400).json({ message: 'No image file uploaded' });
  }

  const imagePath = req.file.path;
  const imageBuffer = fs.readFileSync(imagePath); // Read the image file into a buffer

  // Perform machine learning processing on the image
  // Replace with your image processing code using a machine learning model

  // Delete the uploaded file after processing
  fs.unlinkSync(imagePath);

  // Display the output of the machine learning process
  const output = 'Output from the machine learning model';
  res.json({ output });
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
      res.json({ buah: fruits });
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