import dao.Dao
import dao.StudentDao
import model.Student
import kotlin.system.exitProcess

class App {
    private val dao: Dao<Student, String> = StudentDao()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().startApp()
        }
    }

    private fun startApp() {
        navigateToMainMenu()
    }

    private fun printHeader() {
        println(
            """
            =========================================
            Aplikasi Data Mahasiswa
            =========================================
            1. Cetak Data Mahasiswa
            2. Tambah Data Mahasiswa
            3. Hapus Data Mahasiswa
            4. Keluar
            =========================================
            Masukkan Pilihan Anda (1/2/3/4)?
            =========================================
        """.trimIndent()
        )
    }

    private fun navigateToMenu(menu: String) {
        when (menu) {
            "1" -> {
                openMenuCreateStudent()
            }
            "2" -> {
                openMenuInsertStudent()
            }
            "3" -> {
                openMenuDeleteStudent()
            }
            "4" -> {
                exitProcess(0)
            }
            else -> {
                println("No menu matches")
            }
        }
        askToMainMenu()
    }

    private fun openMenuCreateStudent() {
        val students = dao.getData()
        if (students.isNotEmpty()) {
            students.forEachIndexed { index, student ->
                println(
                    """
                =========================================
                Mahasiswa Ke-${index + 1}
                =========================================
                Nama        : ${student.name}
                Nim         : ${student.nim}
                Jurusan     : ${student.major}
                Kelas       : ${student.className}
                Universitas : ${student.university}
            """.trimIndent()
                )
            }
        } else {
            println("=========================================")
            println("Data Mahasiswa Kosong")
        }
    }

    private fun openMenuInsertStudent() {
        println("=========================================")
        println("Insert data Mahasiswa")
        println("=========================================")
        println("Masukkan Nama : ")
        val name = readLine().orEmpty()
        println("Masukkan Nim : ")
        val nim = readLine().orEmpty()
        println("Masukkan Jurusan : ")
        val major = readLine().orEmpty()
        println("Masukkan Kelas : ")
        val className = readLine().orEmpty()
        println("Masukkan Universitas : ")
        val univ = readLine().orEmpty()
        dao.addData(Student(name, nim, major, className, univ))
        println("=========================================")
        println("Insert Data Berhasil")
    }

    private fun openMenuDeleteStudent() {
        println("=========================================")
        println("Hapus data dengan Nim")
        println("=========================================")
        println("Masukkan Nim : ")
        readLine()?.let {
            dao.deleteData(it)
        }
        println("=========================================")
        println("Hapus Data Berhasil")
    }

    private fun askToMainMenu() {
        println(
            """
            =========================================
            Kembali ke menu utama ? (Y/N)
            =========================================
        """.trimIndent()
        )

        if (readLine().equals("Y", ignoreCase = true)) {
            navigateToMainMenu()
        } else {
            exitProcess(0)
        }
    }

    private fun navigateToMainMenu() {
        printHeader()
        readLine()?.let {
            navigateToMenu(it)
        }
    }
}