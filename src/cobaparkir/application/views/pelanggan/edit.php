<div class="container">
    <div class="row mt-3">
        <div class="col-md-6">
            <!-- http://getbootstrap.com/docs/4.1/components/card/ -->
            <div class="card">
                <div class="card-header">
                    Form Edit Data Pelanggan
                </div>
                <div class="card-body">
                <!-- Untuk menampilkan pesan error -->
                <?php if (validation_errors()):?>
                <div class="alert alert-danger" role="alert">
                    <?= validation_errors(); ?>
                </div>
                <?php endif ?>
                    <form action="" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="<?= $pelanggan['id_pelanggan'];?>">
                    <!-- http://getbootstrap.com/docs/4.1/components/card/forms/ -->
                    <div class="form-group">
                        <label for="id_pelanggan">ID Pelanggan</label>
                        <input type="int"
                            class="form-control"
                            id="id_pelanggan"
                            name="id_pelanggan"
                        value="<?= $pelanggan['id_pelanggan'];?>">
                    </div>
                    <div class="form-group">
                        <label for="nama">Nama</label>
                        <input type="text"
                            class="form-control"
                            id="nama"
                            name="nama"
                        value="<?= $pelanggan['nama'];?>">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="text"
                            class="form-control"
                            id="password"
                            name="password"
                        value="<?= $pelanggan['password'];?>">
                    </div>
                    <div class="form-group">
                        <label for="alamat">Alamat</label>
                        <input type="text"
                            class="form-control"
                            id="alamat"
                            name="alamat"
                        value="<?= $pelanggan['alamat'];?>">
                    </div>
                    <div class="form-group">
                        <label for="nomor_plat">No. Plat</label>
                        <input type="text"
                            class="form-control"
                            id="nomor_plat"
                            name="nomor_plat"
                        value="<?= $pelanggan['nomor_plat'];?>">
                    </div>
                    <div class="form-group">
                        <label for="nomor_telepon">No. Telepon</label>
                        <input type="int"
                            class="form-control"
                            id="nomor_telepon"
                            name="nomor_telepon"
                        value="<?= $pelanggan['nomor_telepon'];?>">
                    </div>
                    <div class="form-group">
                        <label for="no_identitas">No. Identitas</label>
                        <input type="int"
                            class="form-control"
                            id="no_identitas"
                            name="no_identitas"
                        value="<?= $pelanggan['no_identitas'];?>">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text"
                            class="form-control"
                            id="email"
                            name="email"
                        value="<?= $pelanggan['email'];?>">
                    </div>
                    <div class="form-group">
                        <label for="huruf_acak">Huruf Acak</label>
                        <input type="text"
                            class="form-control"
                            id="huruf_acak"
                            name="huruf_acak"
                        value="<?= $pelanggan['huruf_acak'];?>">
                    </div>

                    <button type="submit" name="submit" class="btn btn-primary float-right" > Edit </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>