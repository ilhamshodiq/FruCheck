#!/usr/bin/env python
# coding: utf-8

# In[ ]:


### FOR SERVER PURPOSES ###

import os
from flask import Flask, request
from PIL import Image
import numpy as np
import tensorflow as tf
import tensorflow.keras.models import load_model
from google.cloud import storage

app = Flask(name)

# Initialize the H5 model
model = load_model('./Model/model-frucheck.h5')

# Initialize the Google Cloud storage client
os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = "./serviceAccountKey.json"
storage_client = storage.Client()
bucket_name = 'history-user'

@app.route('/upload', methods=['POST'])
def upload_image():
    if 'image' not in request.files:
        return 'No image uploaded!', 400

    image_file = request.files['image']
    
    if image_file:
        temp_image_path = 'temp_image.jpg'
        image_file.save(temp_image_path)
        
        image = Image.open(temp_image_path)
        image = image.convert('RGB')
        image = image.resize((64, 64))
        image_array = np.array(image) / 255.0
        input_image = np.expand_dims(image_array, axis=0)
        
        class_names = ['ApelSegar', 
                       'PisangSegar', 
                       'WortelSegar', 
                       'JerukSegar', 
                       'KentangSegar', 
                       'TomatSegar', 
                       'ApelBusuk', 
                       'PisangBusuk', 
                       'WortelBusuk', 
                       'JerukBusuk', 
                       'KentangBusuk', 
                       'TomatBusuk']

        predictions = model.predict(input_image)
        predicted_class = np.argmax(predictions)

        if predicted_class >= len(class_names):
            return 'Invalid Prediction Index: ' + str(predicted_class) + '\nLength of class_names: ' + str(len(class_names)), 500
        
        predicted_label = class_names[predicted_class]
        predicted_label_str = str(predicted_label)
        
        bucket = storage_client.bucket(bucket_name)
        blob = bucket.blob(image_file.filename)
        blob.upload_from_filename(temp_image_path)
        
        os.remove(temp_image_path)
        
        label_predicted = predicted_label.replace('Segar', '').replace('Busuk', '')
        freshness = 'Segar' if 'Segar' in predicted_label else 'Busuk'
        
        predicted_prob = np.max(predictions)
        accuracy = predicted_prob * 100
        
        print('Image:', temp_image_path)
        print('Predicted Class:', label_predicted)
        print('Freshness:', freshness)
        print('Accuracy: {:.2f}%'.format(accuracy))

        return 'Image uploaded and scanned!'

    return 'No image uploaded!', 400

if name == '_main_':
    app.run()

