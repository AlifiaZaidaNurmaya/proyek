<div class="container">
    <?php if ($this->session->flashdata('flash-data')) : ?>
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Data Transaksi <strong> berhasil </strong> <?= $this->session->flashdata('flash-data'); ?>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    <?php endif; ?>
    <div class="row mt-4">
        <div class="col-md-6">
            <a href="<?= base_url(); ?>transaksi/tambah" class="btn btn-primary"> Tambah Data </a>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-6">
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Cari Data Transaksi" name="keyword">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Cari</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-6">
            <h2> Daftar Transaksi </h2>

            <!-- alert -->
            <?php if (empty($transaksi)) : ?>
                <div class="alert alert-danger" role="alert">
                    Data Transaksi Tidak ditemukan
                </div>
            <?php endif; ?>

            <div class="table-responsive-md">
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">ID Transaksi</th>
                        <th scope="col">ID Entry</th>
                        <th scope="col">Jenis Transaksi</th>
                        <th scope="col">Jam Checkout</th>
                        <th scope="col">Total</th>
                        <th scope="col">ID Petugas</th>
                        <th scope="col">Hapus</th>
                        <th scope="col">Edit</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                     <?php foreach ($transaksi as $t) { ?>
                    <tr>
                        <td><?php echo $t['id_transaksi']; ?></td>
                        <td><?php echo $t['id_entry']; ?></td>
                        <td><?php echo $t['jenis_transaksi']; ?></td>
                        <td><?php echo $t['jam_checkout']; ?></td>
                        <td><?php echo $t['total']; ?></td>
                        <td><?php echo $t['id_petugas']; ?></td>
                        <td><a href="<?= base_url(); ?>transaksi/hapus/<?= $t['id_transaksi']; ?>" class="badge badge-danger float-right" onclick="return confirm('Yakin Data ini akan dihapus');">Hapus</a></td>
                        <td><a href="<?= base_url(); ?>transaksi/edit/<?= $t['id_transaksi']; ?>" class="badge badge-success float-right">Edit</a></td>
                    </tr>
                <?php } ?>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</div>