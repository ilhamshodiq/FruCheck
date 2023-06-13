#!/usr/bin/env python
# coding: utf-8

# In[ ]:


### FOR PREDICTION USING TF SERVING ###
import requests
import numpy as np
from PIL import Image

# URL endpoint TensorFlow Serving
url = "http://localhost:8501/v1/models/model_name:predict"  # endpoint URL

# Load and preprocess the image
image_path = "../Datasets/Test/RottenCarrot/Carrot_Rotten-64_augmented.jpg"  # Image path
image = Image.open(image_path)
image = image.resize((64, 64))
image_array = np.array(image) / 255.0
input_image = np.expand_dims(image_array, axis=0)

# Convert input to JSON format
input_data = {
    "instances": input_image.tolist()
}

# Send prediction request
response = requests.post(url, json=input_data)

# Get prediction result
predictions = np.array(response.json()["predictions"][0])

# Get predicted class index
predicted_class = np.argmax(predictions)
predicted_label = class_names[predicted_class]

# CLASSIFICATION RESULTS VARIABLES
label_predicted = predicted_label.replace('Segar', '').replace('Busuk', '') # Klasifikasi nama buah/sayur
freshness = 'Segar' if 'Segar' in class_name else 'Busuk' # Menentukan segar atau busuk

# Calculate prediction accuracy
predicted_prob = np.max(predictions)
accuracy = predicted_prob * 100

# Print the predicted class, freshness, and accuracy
print('Image:', image_path)
print('Predicted Class:', label_predicted)
print('Freshness:', freshness)
print('Accuracy: {:.2f}%'.format(accuracy))

