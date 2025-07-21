🔧 Tujuan Umum
Kode ini adalah konfigurasi database primer (primary) untuk sebuah aplikasi Spring Boot yang mengakses lebih dari satu database. File ini bertanggung jawab mengatur:

DataSource (koneksi database)

EntityManagerFactory (untuk ORM)

TransactionManager (untuk transaksi)

Menentukan lokasi repository dan model yang menggunakan konfigurasi ini.

🧩 Penjelasan per Bagian Kode
1. Annotation Kelas
java
Copy
Edit
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "appdbEntityManagerFactory",
        transactionManagerRef = "appdbTransactionManager",
        basePackages = "com.restfullApi.multidb.repository.appdb"
)
@Configuration: Menandakan kelas ini adalah konfigurasi Spring.

@EnableTransactionManagement: Mengaktifkan manajemen transaksi otomatis.

@EnableJpaRepositories:

entityManagerFactoryRef: Menentukan bean EntityManagerFactory untuk repo ini.

transactionManagerRef: Menentukan bean TransactionManager.

basePackages: Lokasi package yang berisi JPA repository untuk koneksi ini.

2. DataSource Bean
java
Copy
Edit
@Primary
@Bean(name = "appdbDataSource")
@ConfigurationProperties(prefix = "db.appdb")
public DataSource appdbDataSource() {
    return DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .build();
}
@Primary: Menandakan ini adalah default DataSource (digunakan jika tidak ada qualifier).

@ConfigurationProperties(prefix = "db.appdb"): Mengambil konfigurasi dari application.properties atau application.yml dengan prefix db.appdb, misalnya:

properties
Copy
Edit
db.appdb.url=jdbc:mysql://localhost:3306/app_db
db.appdb.username=root
db.appdb.password=1234
db.appdb.driver-class-name=com.mysql.cj.jdbc.Driver
HikariDataSource: Pool koneksi performa tinggi.

3. EntityManagerFactory Bean
java
Copy
Edit
@Primary
@Bean(name = "appdbEntityManagerFactory")
public LocalContainerEntityManagerFactoryBean appdbEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("appdbDataSource") DataSource dataSource) {
    return builder
            .dataSource(dataSource)
            .packages("com.restfullApi.multidb.model.appdb")
            .persistenceUnit("appdb")
            .build();
}
@Qualifier("appdbDataSource"): Menggunakan DataSource yang telah didefinisikan sebelumnya.

.packages(...): Lokasi entity/model JPA.

.persistenceUnit("appdb"): Nama persistence unit (penting jika pakai lebih dari 1 DB).

Menghasilkan EntityManagerFactory untuk ORM.

4. TransactionManager Bean
java
Copy
Edit
@Primary
@Bean(name = "appdbTransactionManager")
public PlatformTransactionManager appdbTransactionManager(
        @Qualifier("appdbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
}
Mengelola transaksi JPA.

Menggunakan JpaTransactionManager.

Mengarah ke EntityManagerFactory yang sebelumnya didefinisikan.

📦 Ringkasan Flow Konfigurasi
Spring membaca konfigurasi dari file properti dengan prefix db.appdb.

Membuat koneksi ke database (DataSource) menggunakan HikariCP.

Membangun EntityManagerFactory untuk mengelola entity (ORM).

Mengatur TransactionManager agar transaksi antar operasi database dikelola dengan benar.

Mengarahkan repository pada path com.restfullApi.multidb.repository.appdb untuk memakai konfigurasi ini.

💡 Catatan Tambahan
Konfigurasi ini wajib dipisahkan jika kamu menggunakan multiple DB (misalnya appdb + edukasi).

Biasanya ada file konfigurasi lain untuk database kedua, yang hampir identik tapi tanpa @Primary.


