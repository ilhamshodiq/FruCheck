import os
from flask import Flask, request, jsonify
from PIL import Image
import numpy as np
import tensorflow as tf 
import keras as keras
from google.cloud import storage
from flask_cors import CORS

app = Flask(__name__)
CORS(app)
# Initialize the H5 model
model = keras.models.load_model('model-frucheck.h5')

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
        image = Image.open(image_file)
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
        
        predicted_label = class_names[predicted_class]
        
        bucket = storage_client.bucket(bucket_name)
        blob = bucket.blob(image_file.filename)
        blob.upload_from_string(
            image_file.read(),
            content_type=image_file.content_type
        )
        
        label_predicted = predicted_label.replace('Segar', '').replace('Busuk', '')
        freshness = 'Segar' if 'Segar' in predicted_label else 'Busuk'
        
        predicted_prob = np.max(predictions)
        accuracy = predicted_prob * 100
        
        print('Image:', image_file.filename)
        print('Predicted Class:', label_predicted)
        print('Freshness:', freshness)
        print('Accuracy: {:.2f}%'.format(accuracy))

        result = {
            'Message': 'Image uploaded and scanned!',
            'Predicted Class': label_predicted,
            'Freshness': freshness,
            'Accuracy': accuracy
        }
        print(result)

        # Return the result as JSON response
        return jsonify(result), 200

    return jsonify({'No image uploaded!'}), 400

if __name__== '__main__':
    app.run(debug=True)
