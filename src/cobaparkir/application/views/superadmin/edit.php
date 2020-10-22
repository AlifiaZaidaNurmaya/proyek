<div class="container">
    <div class="row mt-3">
        <div class="col-md-6">
            <!-- http://getbootstrap.com/docs/4.1/components/card/ -->
            <div class="card">
                <div class="card-header">
                    Form Edit Data Admin
                </div>
                <div class="card-body">
                <!-- Untuk menampilkan pesan error -->
                <?php if (validation_errors()):?>
                <div class="alert alert-danger" role="alert">
                    <?= validation_errors(); ?>
                </div>
                <?php endif ?>
                    <form action="" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="<?= $super_admin['id_admin'];?>">
                    <!-- http://getbootstrap.com/docs/4.1/components/card/forms/ -->
                    <div class="form-group">
                        <label for="id_admin">ID Admin</label>
                        <input type="int"
                            class="form-control"
                            id="id_admin"
                            name="id_admin"
                        value="<?= $super_admin['id_admin'];?>">
                    </div>
                    <div class="form-group">
                        <label for="nama">Nama</label>
                        <input type="text"
                            class="form-control"
                            id="nama"
                            name="nama"
                        value="<?= $super_admin['nama'];?>">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text"
                            class="form-control"
                            id="email"
                            name="email"
                        value="<?= $super_admin['email'];?>">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="text"
                            class="form-control"
                            id="password"
                            name="password"
                        value="<?= $super_admin['password'];?>">
                    </div>
                    <div class="form-group">
                        <label for="alamat">Alamat</label>
                        <input type="text"
                            class="form-control"
                            id="alamat"
                            name="alamat"
                        value="<?= $super_admin['alamat'];?>">
                    </div>
                    <div class="form-group">
                        <label for="no_telepon">No. Telepon</label>
                        <input type="int"
                            class="form-control"
                            id="no_telepon"
                            name="no_telepon"
                        value="<?= $super_admin['no_telepon'];?>">
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary float-right" > Edit </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>