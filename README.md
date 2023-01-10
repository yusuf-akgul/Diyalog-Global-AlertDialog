# Diyalog-Global-AlertDialog

Açık kaynak kodlu kotlin class.
Projenin tamamında kullanılabilecek global bir AlertDialog nesnesi
Uygulama kotlin dili ile yazılmıştır, en düşük android SDK=23 ver.=6 dır.
henüz library haline getirilmemiştir.

Kullanımı aşağıdaki gibidir.
setResult fonksiyonu ile geri dönüşler string olarak alınır.
geri dönüş değerleri: Butonlar için "OK", "NO" 
ve adapter kullanılmışsa  adapter sıra numaralarıdır. ("1","2" gibi.) 

Diyalog()
	.Init(contxt)
	.setIcon(R.drawable.info48) 
	.setColor(Diyalog().BEYAZ, Diyalog().MAVI) 
	.setTitle("Kayıt Silme")
	.setMessage("Bu kaysı silmek istediğinizden emin misiniz ?")
	.setAnime(R.style.anime) 
	.setPozBtn("Sil")
	.setNegBtn("Vazgeç")
	.setResult {
		if (it=="OK"){
			..............
		}else{
			..............
		}
	}
.Show()

Kolay gelsin

Yusuf AKGÜL
y.akgul64@gmail.com


