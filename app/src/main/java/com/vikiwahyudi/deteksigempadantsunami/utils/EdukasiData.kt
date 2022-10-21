package com.vikiwahyudi.deteksigempadantsunami.utils

object EdukasiData {

    fun sebelumGempa(): Edukasi = Edukasi("Sebelum Gempa Terjadi", getSebelumGempaTerjadi())
    fun saatGempa(): Edukasi = Edukasi("Saat Gempa Terjadi", getSaatGempaTerjadi())
    fun setelahGempa(): Edukasi = Edukasi("Setelah Gempa Terjadi", getSetelahGempaTerjadi())

    fun getSebelumGempaTerjadi(): List<EdukasiEntity> {
        return listOf(
            EdukasiEntity(
                "A. Kunci Utama adalah\n" +"\n" +
                        "Mengenali apa yang disebut gempabumi " +
                        "Pastikan bahwa struktur dan letak rumah Anda dapat terhindar dari bahaya yang disebabkan oleh gempabumi (longsor, liquefaction dll) " +
                        "Mengevaluasi dan merenovasi ulang struktur bangunan Anda agar terhindar dari bahaya gempa bumi.",


                "Mengenali apa yang disebut gempabumi;\n" +
                        "Pastikan bahwa struktur dan letak rumah Anda dapat terhindar dari bahaya yang disebabkan oleh gempabumi (longsor, liquefaction dll);\n" +
                        "Mengevaluasi dan merenovasi ulang struktur bangunan Anda agar terhindar dari bahaya gempa bumi.",

                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi01.png"
            ),
            EdukasiEntity(
                "B. Kenali Lingkungan Tempat Anda Bekerja\n" +"\n" +
                        "Perhatikan letak pintu, lift serta tangga darurat, apabila terjadi gempa bumi, sudah mengetahui tempat paling aman untuk berlindung " +
                        "Belajar melakukan P3K " +
                        "Belajar menggunakan alat pemadam kebakaran " +
                        "Catat nomor telepon penting yang dapat dihubungi pada saat terjadi gempa bumi.",

                "Perhatikan letak pintu, lift serta tangga darurat, apabila terjadi gempa bumi, sudah mengetahui tempat paling aman untuk berlindung;\n" +
                        "Belajar melakukan P3K;\n" +
                        "Belajar menggunakan alat pemadam kebakaran;\n" +
                        "Catat nomor telepon penting yang dapat dihubungi pada saat terjadi gempa bumi.",

                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi02.png"
            ),

            EdukasiEntity(
                "C. Persiapan Rutin pada tempat Anda bekerja dan tinggal\n" + "\n" +
                        "Perabotan (lemari, cabinet, dll) diatur menempel pada dinding (dipaku, diikat, dll) untuk menghindari jatuh, roboh, bergeser pada saat terjadi gempa bumi. " +
                        "Simpan bahan yang mudah terbakar pada tempat yang tidak mudah pecah agar terhindar dari kebakaran. " +
                        "Selalu mematikan air, gas dan listrik apabila tidak sedang digunakan.",

                "Perabotan (lemari, cabinet, dll) diatur menempel pada dinding (dipaku, diikat, dll) untuk menghindari jatuh, roboh, bergeser pada saat terjadi gempa bumi.\n" +
                        "Simpan bahan yang mudah terbakar pada tempat yang tidak mudah pecah agar terhindar dari kebakaran.\n" +
                        "Selalu mematikan air, gas dan listrik apabila tidak sedang digunakan.",

                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi03.png"
            ),

            EdukasiEntity(
                "D. Penyebab celaka yang paling banyak pada saat gempa bumi adalah akibat kejatuhan material\n" + "\n" +
                        "Atur benda yang berat sedapat mungkin berada pada bagian bawah",

                "Atur benda yang berat sedapat mungkin berada pada bagian bawah\n" +
                        "Cek kestabilan benda yang tergantung yang dapat jatuh pada saat gempabumi terjadi (misalnya lampu dll).",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi04.png"

            ),
            EdukasiEntity(
                "E. Alat yang harus ada di setiap tempat\n" +"\n" +

                        "Kotak P3K;\n" +
                        "Senter/lampu baterai;\n" +
                        "Radio;\n" +
                        "Makanan suplemen dan air.",


                "Kotak P3K;\n" +
                        "Senter/lampu baterai;\n" +
                        "Radio;\n" +
                        "Makanan suplemen dan air.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi05.png"
            )
        )
    }

    fun getSaatGempaTerjadi(): List<EdukasiEntity> {

        return listOf(
            EdukasiEntity(
                "A. Jika Anda berada di dalam bangunan\n" + "\n" +
                        "Lindungi badan dan kepala Anda dari reruntuhan bangunan dengan bersembunyi di bawah meja dll " +
                        "Cari tempat yang paling aman dari reruntuhan dan goncangan " +
                        "Lari ke luar apabila masih dapat dilakukan",

                "Lindungi badan dan kepala Anda dari reruntuhan bangunan dengan bersembunyi di bawah meja dll;\n" +
                        "Cari tempat yang paling aman dari reruntuhan dan goncangan;\n" +
                        "Lari ke luar apabila masih dapat dilakukan",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi06.png"
            ),

            EdukasiEntity(
                "B. Jika berada di luar bangunan atau area terbuka\n" + "\n" +
                        "Menghindari dari bangunan yang ada di sekitar Anda seperti gedung, tiang listrik, pohon, dll " +
                        "Perhatikan tempat Anda berpijak, hindari apabila terjadi rekahan tanah",

                "Menghindari dari bangunan yang ada di sekitar Anda seperti gedung, tiang listrik, pohon, dll\n" +
                        "Perhatikan tempat Anda berpijak, hindari apabila terjadi rekahan tanah",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi07.png"
            ),
            EdukasiEntity(
                "C. Jika Anda sedang mengendarai mobil\n" + "\n" +
                        "Jika Anda sedang mengendarai mobil " +
                        "Keluar, turun dan menjauh dari mobil hindari jika terjadi pergeseran atau kebakaran " +
                        "Lakukan point B.",

                "Jika Anda sedang mengendarai mobil\n" +
                        "Keluar, turun dan menjauh dari mobil hindari jika terjadi pergeseran atau kebakaran;\n" +
                        "Lakukan point B.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi08.png"
            ),

            EdukasiEntity(
                "D. Jika Anda tinggal atau berada di pantai\n" + "\n" +
                        "Jauhi pantai untuk menghindari bahaya tsunami.",

                "Jauhi pantai untuk menghindari bahaya tsunami.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi09.png"
            ),

            EdukasiEntity(
                "E. Jika Anda tinggal di daerah pegunungan\n" + "\n" +

                        "Apabila terjadi gempa bumi hindari daerah yang mungkin terjadi longsoran.",


                "Apabila terjadi gempa bumi hindari daerah yang mungkin terjadi longsoran.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi10.png"
            ),
        )
    }

    fun getSetelahGempaTerjadi(): List<EdukasiEntity> {
        return listOf(
            EdukasiEntity(
                "A. Jika Anda berada di dalam bangunan\n" +"\n" +

                        "Keluar dari bangunan tersebut dengan tertib " +
                        "Jangan menggunakan tangga berjalan atau lift, gunakan tangga biasa " +
                        "Periksa apa ada yang terluka, lakukan P3K " +
                        "Telepon atau mintalah pertolongan apabila terjadi luka parah pada Anda atau sekitar Anda.",

                "Keluar dari bangunan tersebut dengan tertib;\n" +
                        "Jangan menggunakan tangga berjalan atau lift, gunakan tangga biasa;\n" +
                        "Periksa apa ada yang terluka, lakukan P3K;\n" +
                        "Telepon atau mintalah pertolongan apabila terjadi luka parah pada Anda atau sekitar Anda.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi11.png"
            ),
            EdukasiEntity(
                "B. Periksa lingkungan sekitar Anda\n" + "\n" +

                        "Periksa apabila terjadi kebakaran.\n" +
                        "Periksa apabila terjadi kebocoran gas.\n" +
                        "Periksa apabila terjadi hubungan arus pendek listrik.\n" +
                        "Periksa aliran dan pipa air.\n" +
                        "Periksa apabila ada hal-hal yang membahayakan (mematikan listrik, tidak menyalakan  dll)",


                "Periksa apabila terjadi kebakaran.\n" +
                        "Periksa apabila terjadi kebocoran gas.\n" +
                        "Periksa apabila terjadi hubungan arus pendek listrik.\n" +
                        "Periksa aliran dan pipa air.\n" +
                        "Periksa apabila ada hal-hal yang membahayakan (mematikan listrik, tidak menyalakan  dll)",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi12.png"
            ),
            EdukasiEntity(
                "C. Jangan mamasuki bangunan yang sudah terkena gempa\n" + "\n" +

                        "Karena kemungkinan masih terdapat reruntuhan.",

                "Karena kemungkinan masih terdapat reruntuhan.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi13.png"
            ),

            EdukasiEntity(
                "D. Jangan berjalan di daerah sekitar gempa\n" +"\n" +

                        "Kemungkinan terjadi bahaya susulan masih ada.",

                "Kemungkinan terjadi bahaya susulan masih ada.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi14.png"
            ),

            EdukasiEntity(
                "E. Mendengarkan informasi\n" + "\n" +
                        "Dengarkan informasi mengenai gempa bumi dari radio (apabila terjadi gempa susulan). " +
                        "Jangan mudah terpancing oleh isu atau berita yang tidak jelas sumbernya.",

                "Dengarkan informasi mengenai gempa bumi dari radio (apabila terjadi gempa susulan).\n" +
                        "Jangan mudah terpancing oleh isu atau berita yang tidak jelas sumbernya.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi15.png"
            ),

            EdukasiEntity(
                "F. Mengisi Angket\n" + "\n" +
                        "Mengisi angket yang diberikan oleh instansi terkait untuk mengetahui seberapa besar kerusakan yang terjadi",
                "Mengisi angket yang diberikan oleh instansi terkait untuk mengetahui seberapa besar kerusakan yang terjadi",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi16.png"
            ),

            EdukasiEntity(
                "G. Berdoa\n" + "\n" +
                        "Jangan panik dan jangan lupa selalu berdo'a kepada Tuhan YME demi keamanan dan keselamatan kita semuanya.",

                "Jangan panik dan jangan lupa selalu berdo'a kepada Tuhan YME demi keamanan dan keselamatan kita semuanya.",
                "https://www.bmkg.go.id/asset/img/gempabumi/antisipasi17.png"
            )
        )
    }
}