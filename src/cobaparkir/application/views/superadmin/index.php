<div class="container">
    <?php if ($this->session->flashdata('flash-data')) : ?>
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Data Admin <strong> berhasil </strong> <?= $this->session->flashdata('flash-data'); ?>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    <?php endif; ?>
    <div class="row mt-4">
        <div class="col-md-6">
            <a href="<?= base_url(); ?>superadmin/tambah" class="btn btn-primary"> Tambah Data </a>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-6">
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Cari Data Admin" name="keyword">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Cari</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-6">
            <h2> Daftar Admin </h2>

            <!-- alert -->
            <?php if (empty($super_admin)) : ?>
                <div class="alert alert-danger" role="alert">
                    Data Admin Tidak ditemukan
                </div>
            <?php endif; ?>

            <div class="table-responsive-md">
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">ID Admin</th>
                        <th scope="col">Nama</th>
                        <th scope="col">Email</th>
                        <th scope="col">Username</th>
                        <th scope="col">Password</th>
                        <th scope="col">Alamat</th>
                        <th scope="col">No. Telepon</th>
                        <th scope="col">Hapus</th>
                        <th scope="col">Edit</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                     <?php foreach ($super_admin as $sa) { ?>
                    <tr>
                        <td><?php echo $sa['id_admin']; ?></td>
                        <td><?php echo $sa['nama']; ?></td>
                        <td><?php echo $sa['email']; ?></td>
                        <td><?php echo $sa['username']; ?></td>
                        <td><?php echo $sa['password']; ?></td>
                        <td><?php echo $sa['alamat']; ?></td>
                        <td><?php echo $sa['no_telepon']; ?></td>
                        <td><a href="<?= base_url(); ?>superadmin/hapus/<?= $sa['id_admin']; ?>" class="badge badge-danger float-right" onclick="return confirm('Yakin Data ini akan dihapus');">Hapus</a></td>
                        <td><a href="<?= base_url(); ?>superadmin/edit/<?= $sa['id_admin']; ?>" class="badge badge-success float-right">Edit</a></td>
                    </tr>
                <?php } ?>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</div>