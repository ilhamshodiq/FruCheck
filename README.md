# Dokumentasi CC
API untuk melakukan pengecekan kebusukan sebuah buah

# Endpoint 
- Upload 
- Buah 
- Buah/ :id

## Upload 
Endpoint untuk mengunggah gambar
- Request 
   - 	Method: POST
   - 	URL: /upload
   - 	Body:
         - 	image (file): The image file to be scanned

- Response 
  - 	Status Code: 200 OK 
        { "accuracy": 99.30139183998108,
          "freshness" : "Busuk" ,
          "image" : "temp_image.jpg",
          "message" : "Image uploaded and scanned!In",
          "predicted_class" : "Tomat"
        }


## Buah
Endpoint untuk menampilkan tampilan list buah 
- Request 
   - 	Method: POST
   - 	URL: /upload
   - 	Body:
         - 	image (file): The image file to be scanned

- Response 
  - 	Status Code: 200 OK 
        { 
{

    "message": "Data buah berhasil diambil",
    "error": false,
    "buah": [
        {
            "nama": "wortel",
            "manfaat": [
                "Mengurangi resiko terjadinya kanker",
                "Menurunkan kolesterol darah",
                "Menurunkan berat badan",
                "Membantu kesehatan mata"
            ],
            "nutrisi": [
                "41 kalori",
                "88% air",
                "0,9 gram protein",
                "9,6 gram karbohidrat",
                "4,7 gram gula",
                "2,8 gram serat",
                "0,2 gram lemak"
            ],
            "penyimpanan": [
                "Jangan mencuci wortel sampai tepat sebelum Anda ingin menggunakannya, karena air dapat menyebabkan kelembaban berlebih yang akan mempercepat proses pembusukan.",
                "Bungkus wortel dengan tisu dan simpan dalam wadah kedap udara. Simpan wortel di dalam kulkas dan jauhkan dari buah-buahan yang menghasilkan gas etilen, seperti apel untuk mencegah kebusukan."
            ],
            "olahan": [
                "Brownies kukus wortel",
                "Cupcake wortel"
            ],
            "photoUrl": "https://cdn.discordapp.com/attachments/1117384900132229251/1117389880910163998/carrot_svgrepo.com.png",
            "id": "1Z0t3UQuSWdlU83TaBQo"
        },
        {
            "nama": "apel",
            "manfaat": [
                "Dapat menurunkan kolesterol",
                "Dapat mencegah diabetes",
                "Dapat mencegah obesitas",
                "Dapat melindungi dari penyakit jantung",
                "Bermanfaat untuk kesehatan tulang"
            ],
            "nutrisi": [
                "95 kalori",
                "95 kalori",
                "25 gram karbohidrat",
                "19 gram gula",
                "3 gram serat"
            ],
            "penyimpanan": [
                "Penyimpanan dalam kulkas, apel dapat bertahan kesegarannya selama 1 - 2 bulan.",
                "Penyimpanan dalam suhu ruangan, apel dapat bertahan kesegarannya selama sekitar 1 - 2 minggu."
            ],
            "olahan": [
                "Jus apel",
                "Pie apel",
                "Keripik apel",
                "Selai apel",
                "Strudel apel"
            ],
            "photoUrl": "https://cdn.discordapp.com/attachments/1117384900132229251/1117389830687559700/Group_2.png",
            "id": "ZhdBiWAOmPsCfSjY85hb"
        },
        {
            "manfaat": [
                "Menjaga tubuh agar selalu terhidrasi",
                "Memperbaiki pencernaan",
                "Mengurangi lemak perut",
                "Mendukung sistem kekebalan tubuh",
                "Membantu penyerapan zat besi",
                "Melindungi dari penyakit kronis",
                "Mengurangi resiko terjadi kanker"
            ],
            "nutrisi": [
                "72,8 kalori",
                "0,21 gram lemak",
                "12,6 mg natrium",
                "16,5 gram karbohidrat",
                "2,8 gram serat",
                "1,27 gram protein"
            ],
            "penyimpanan": [
                "Jangan mencuci jeruk sampai tepat sebelum Anda ingin memakannya, karena air dapat menyebabkan kelembaban berlebih yang akan mempercepat proses pembusukan.",
                "Tempatkan jeruk kering pada kantong ziplock atau wadah penyimpanan kedap udara.",
                "Penyimpanan dalam kulkas dapat bertahan hingga 1 bulan."
            ],
            "olahan": [
                "Jus jeruk",
                "Pie jeruk"
            ],
            "nama": "jeruk",
            "photoUrl": "https://cdn.discordapp.com/attachments/1117384900132229251/1117389863419924500/Orange.png",
            "id": "k4hZfQz3wgioJwuTsgRO"
        },
        {
            "nama": "kentang",
            "manfaat": [
                "Mengandung antioxidant",
                "Dapat meningkatkan kontrol gula darah",
                "Dapat meningkatkan kesehatan pencernaan",
                "Rendah lemak",
                "Mendukung kesehatan usus"
            ],
            "nutrisi": [
                "119 kcal",
                "3,1 gram protein",
                "0,2 gram protein",
                "26,1 gram karbohidrat",
                "1,9 gram sugar",
                "3,1 gram serat",
                "12 mg Vitamin C"
            ],
            "penyimpanan": [
                "Jangan mencuci kentang sampai tepat sebelum Anda ingin menggunakannya, karena air dapat menyebabkan kelembaban berlebih yang akan mempercepat proses pembusukan.",
                "Simpan kentang mentah di tempat yang dingin.",
                "Jauhkan dari sinar matahari langsung.",
                "Jangan menyimpan kentang mentah di kulkas atau freezer.",
                "Tempatkan di mangkuk terbuka atau kantong kertas."
            ],
            "olahan": [
                "French fries",
                "Perkedel",
                "Kentang keju",
                "Keripik kentang"
            ],
            "photoUrl": "https://cdn.discordapp.com/attachments/1117384900132229251/1117389910312230992/potato_svgrepo.com.png",
            "id": "kjsv0roJqbsTHVhwt736"
        },
        {
            "nama": "tomat",
            "manfaat": [
                "Dapat menjaga kesehatan jantung",
                "Mendukung kesehatan kulit",
                "Mendukung pembekuan darah dan penyembuhan luka",
                "Dapat membantu mengurangi gejala menopause"
            ],
            "penyimpanan": [
                "Simpan dalam plastik kedap udara.",
                "Menutup ujung tomat menggunakan isolasi.",
                "Lap permukaan kulit tomat sebelum dimasukkan ke kulkas.",
                "Bekukan tomat di dalam freezer."
            ],
            "nutrisi": [
                "18 kalori",
                "95% air",
                "0,9 gram protein",
                "3,9 gram karbohidrat",
                "2,6 gram gula",
                "1,2 gram serat",
                "0,2 gram lemak"
            ],
            "olahan": [
                "Jus tomat",
                "Saus tomat"
            ],
            "photoUrl": "https://cdn.discordapp.com/attachments/1117384900132229251/1117389927261405245/tomato_svgrepo.com.png",
            "id": "mpuuiWKouqgOVl3sNvgm"
        },
        {
            "nama": "pisang",
            "manfaat": [
                "Baik untuk kesehatan usus",
                "Dapat mendukung kesehatan jantung",
                "Sebagai booster energi"
            ],
            "nutrisi": [
                "110 kalori",
                "0 gram lemak",
                "1 gram protein",
                "28 gram karbohidrat",
                "15 gram gula",
                "3 gram serat",
                "450 mg potasium"
            ],
            "penyimpanan": [
                "Simpan pada suhu ruangan jauh dari sinar matahari langsung",
                "Jangan menyimpan pisang yang belum matang ke dalam kulkas, karena dapat mengganggu pematangan alaminya.",
                "Untuk mempercepat pematangan, simpan dalam paper bag atau tempatkan di dekat pisang yang sudah matang yang mengeluarkan gas etilen yang menyebabkan kematangan.",
                "Pisang yang matang dapat disimpan dalam kulkas, ini akan mempertahankan rasa dari buah selama 1 minggu, meskipun kulitnya akan terus menjadi gelap."
            ],
            "olahan": [
                "Nugget pisang",
                "Pisang goreng",
                "Pisang bakar",
                "Pisang bolen",
                "Smoothie"
            ],
            "photoUrl": "https://cdn.discordapp.com/attachments/1117384900132229251/1117389850862157864/banana_svgrepo.com.png",
            "id": "oCQ8KDcUdivhsc2DBT7s"
        }
    ]
}
        }

## Buah/:id
Endpoint untuk mengunggah gambar berdasarkan id 
- Request 
   - 	Method: GET
   - 	URL: /buah/:id
   - 	Parameter:
         - 	id (string): The ID of the fruit to retrieve

- Response 
  - 	Status Code: 200 OK 
        { "message": "Data buah berhasil diambil",
            "error": false,
            "buah": [
              {
                "nama": "wortel",
                "manfaat": [
                "Mengurangi resiko terjadinya kanker",
                "Menurunkan kolesterol darah",
                "Menurunkan berat badan",
                "Membantu kesehatan mata"
            ],
            "nutrisi": [
                "41 kalori",
                "88% air",
                "0,9 gram protein",
                "9,6 gram karbohidrat",
                "4,7 gram gula",
                "2,8 gram serat",
                "0,2 gram lemak"
            ],
            "penyimpanan": [
                "Jangan mencuci wortel sampai tepat sebelum Anda ingin menggunakannya, karena air dapat menyebabkan kelembaban berlebih yang akan mempercepat proses pembusukan.",
                "Bungkus wortel dengan tisu dan simpan dalam wadah kedap udara. Simpan wortel di dalam kulkas dan jauhkan dari buah-buahan yang menghasilkan gas etilen, seperti apel untuk mencegah kebusukan."
            ],
            "olahan": [
                "Brownies kukus wortel",
                "Cupcake wortel"
            ],
            "photoUrl": "https://cdn.discordapp.com/attachments/1117384900132229251/1117389880910163998/carrot_svgrepo.com.png",
            "id": "1Z0t3UQuSWdlU83TaBQo"
        },
        }

