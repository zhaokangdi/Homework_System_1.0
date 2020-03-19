# Homework_System_1.0
因为在老师讲解了如何将原项目改造为maven工程及建立数据库连接池后，发现改造maven工程操作不太正确，所以重新建了一个仓库(Homework_System)存储代码。
本仓库代码中数据库连接的优化仅为创建了JdbcUtil类，其中包含Connect()、Close()函数连接、关闭数据库，并在其他类中调用，并未建立数据库连接池。
