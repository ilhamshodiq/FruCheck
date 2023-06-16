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

// API endpoint to fetch fruit data by ID
// API endpoint to fetch all fruit data
app.get("/buah/:id", (req, res) => {
  try {
    const reqDoc = db.collection("daftar_buah").doc(req.params.id);

    reqDoc.get().then((buahDetail) => {
      if (!buahDetail.exists) {
        return res.status(404).send({
          message: 'Tidak ada buah yang ditemukan', 
          error: true, 
          message: "Buah tidak ditemukan" });
      }

      const response = buahDetail.data();
      return res.status(200).send({
        message : 'Data buah berhasil diambil', 
        error: false, 
        data: response });
    });
  } catch (error) {
    console.log(error);
    res.status(500).send({ status: "Failed", message: error });
  }
});

// Start the application server
const port = process.env.PORT || 8080;
app.listen(port, () => {
  console.log(`server berjalan pada http://localhost:${port}`);
});
