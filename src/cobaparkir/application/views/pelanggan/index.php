<div class="container">
    <?php if ($this->session->flashdata('flash-data')) : ?>
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Data Pelanggan <strong> berhasil </strong> <?= $this->session->flashdata('flash-data'); ?>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    <?php endif; ?>
    <div class="row mt-4">
        <div class="col-md-6">
            <a href="<?= base_url(); ?>pelanggan/tambah" class="btn btn-primary"> Tambah Data </a>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-6">
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Cari Data Pelanggan" name="keyword">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Cari</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-6">
            <h2> Daftar Pelanggan </h2>

            <!-- alert -->
            <?php if (empty($pelanggan)) : ?>
                <div class="alert alert-danger" role="alert">
                    Data Pelanggan Tidak ditemukan
                </div>
            <?php endif; ?>

            <div class="table-responsive-md">
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">ID Pelanggan</th>
                        <th scope="col">Nama</th>
                        <th scope="col">Username</th>
                        <th scope="col">Password</th>
                        <th scope="col">Alamat</th>
                        <th scope="col">No. Plat</th>
                        <th scope="col">No. Telepon</th>
                        <th scope="col">No. Identitas</th>
                        <th scope="col">Email</th>
                        <th scope="col">Huruf Acak</th>
                        <th scope="col">Hapus</th>
                        <th scope="col">Edit</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                     <?php foreach ($pelanggan as $p) { ?>
                    <tr>
                        <td><?php echo $p['id_pelanggan']; ?></td>
                        <td><?php echo $p['nama']; ?></td>
                        <td><?php echo $p['username']; ?></td>
                        <td><?php echo $p['password']; ?></td>
                        <td><?php echo $p['alamat']; ?></td>
                        <td><?php echo $p['nomor_plat']; ?></td>
                        <td><?php echo $p['nomor_telepon']; ?></td>
                        <td><?php echo $p['no_identitas']; ?></td>
                        <td><?php echo $p['email']; ?></td>
                        <td><?php echo $p['huruf_acak']; ?></td>
                        <td><a href="<?= base_url(); ?>pelanggan/hapus/<?= $p['id_pelanggan']; ?>" class="badge badge-danger float-right" onclick="return confirm('Yakin Data ini akan dihapus');">Hapus</a></td>
                        <td><a href="<?= base_url(); ?>pelanggan/edit/<?= $p['id_pelanggan']; ?>" class="badge badge-success float-right">Edit</a></td>
                    </tr>
                <?php } ?>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</div>