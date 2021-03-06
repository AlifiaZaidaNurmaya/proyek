<div class="container" style="margin-left:300px;">
    <div class="row mt-3">
        <div class="col-md-6">
            <!-- http://getbootstrap.com/docs/4.1/components/card/ -->
            <div class="card">
                <div class="card-header">
                    Form Edit Data Petugas
                </div>
                <div class="card-body">
                <!-- Untuk menampilkan pesan error -->
                <?php if (validation_errors()):?>
                <div class="alert alert-danger" role="alert">
                    <?= validation_errors(); ?>
                </div>
                <?php endif ?>
                    <form action="" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="<?= $petugas['id_petugas'];?>">
                    <!-- http://getbootstrap.com/docs/4.1/components/card/forms/ -->
                    <div class="form-group">
                        <label for="id_petugas">ID Petugas</label>
                        <input type="int"
                            class="form-control"
                            id="id_petugas"
                            name="id_petugas"
                        value="<?= $petugas['id_petugas'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="nama">Nama</label>
                        <input type="text"
                            class="form-control"
                            id="nama"
                            name="nama"
                        value="<?= $petugas['nama'];?>">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text"
                            class="form-control"
                            id="email"
                            name="email"
                        value="<?= $petugas['email'];?>">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="text"
                            class="form-control"
                            id="password"
                            name="password"
                        value="<?= $petugas['password'];?>">
                    </div>
                    <div class="form-group">
                        <label for="alamat">Alamat</label>
                        <input type="text"
                            class="form-control"
                            id="alamat"
                            name="alamat"
                        value="<?= $petugas['alamat'];?>">
                    </div>
                    <div class="form-group">
                        <label for="no_telepon">No. Telepon</label>
                        <input type="int"
                            class="form-control"
                            id="no_telepon"
                            name="no_telepon"
                        value="<?= $petugas['no_telepon'];?>">
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary float-right" > Edit </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>