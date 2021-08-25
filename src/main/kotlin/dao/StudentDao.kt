package dao

import model.Student

class StudentDao : Dao<Student, String> {
    private var students = arrayListOf<Student>().apply {
        add(Student("Tony bin Stark", "12345", "Teknik Informatika", "4IA01", "UIN"))
        add(Student("Black Widdow", "12346", "Teknik Elektro", "4IA02", "GUNDAR"))
        add(Student("Bruce", "12347", "Teknik Kimia", "4IA03", "ITB"))
        add(Student("Captain", "12348", "Teknik Fisika", "4IA04", "UI"))
        add(Student("Thoriq", "12349", "Teknik Industri", "4IA05", "UNTIRTA"))
    }

    override fun getData(): List<Student> {
        return students
    }

    override fun addData(item: Student) {
        students.add(item)
    }

    override fun deleteData(uniqueID: String) {
        /*
        for (student in students) {
            if (student.nim == uniqueID) {
                students.remove(student)
            }
        }
        */

        students.remove(students.find { student ->
            student.nim == uniqueID
        })
    }
}