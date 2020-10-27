<div class="container">
    <?php if ($this->session->flashdata('flash-data')) : ?>
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Data Booking <strong> berhasil </strong> <?= $this->session->flashdata('flash-data'); ?>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    <?php endif; ?>
    <div class="row mt-4">
        <div class="col-md-6">
            <a href="<?= base_url(); ?>booking/tambah" class="btn btn-primary"> Tambah Data </a>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-6">
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Cari Data Booking" name="keyword">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Cari</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-6">
            <h2> Daftar Booking </h2>

            <!-- alert -->
            <?php if (empty($booking)) : ?>
                <div class="alert alert-danger" role="alert">
                    Data Booking Tidak ditemukan
                </div>
            <?php endif; ?>

            <div class="table-responsive-md">
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">ID Booking</th>
                        <th scope="col">ID Pelanggan</th>
                        <th scope="col">No. Parkir</th>
                        <th scope="col">Jam Booking</th>
                        <th scope="col">Hapus</th>
                        <th scope="col">Edit</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                     <?php foreach ($booking as $b) { ?>
                    <tr>
                        <td><?php echo $b['id_booking']; ?></td>
                        <td><?php echo $b['id_pelanggan']; ?></td>
                        <td><?php echo $b['no_parkir']; ?></td>
                        <td><?php echo $b['jam_booking']; ?></td>
                        <td><a href="<?= base_url(); ?>booking/hapus/<?= $b['id_booking']; ?>" class="badge badge-danger float-right" onclick="return confirm('Yakin Data ini akan dihapus');">Hapus</a></td>
                        <td><a href="<?= base_url(); ?>booking/edit/<?= $b['id_booking']; ?>" class="badge badge-success float-right">Edit</a></td>
                    </tr>
                <?php } ?>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</div>