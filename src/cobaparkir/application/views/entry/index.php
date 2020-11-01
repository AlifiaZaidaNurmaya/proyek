<div class="container">
    <?php if ($this->session->flashdata('flash-data')) : ?>
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Data Entry <strong> berhasil </strong> <?= $this->session->flashdata('flash-data'); ?>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    <?php endif; ?>
    <!-- <div class="row mt-4">
        <div class="col-md-6">
            <a href="<?= base_url(); ?>entry/tambah" class="btn btn-primary"> Tambah Data </a>
        </div>
    </div> -->

    <div class="row mt-4">
        <div class="col-md-6">
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Cari Data Entry" name="keyword">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Cari</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-6">
            <h2> Daftar Entry </h2>

            <!-- alert -->
            <?php if (empty($entry)) : ?>
                <div class="alert alert-danger" role="alert">
                    Data Entry Tidak ditemukan
                </div>
            <?php endif; ?>

            <div class="table-responsive-md">
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">ID Entry</th>
                        <th scope="col">ID Pelanggan</th>
                        <th scope="col">ID Booking</th>
                        <th scope="col">Nama Pelanggan</th>
                        <th scope="col">Nomor Plat</th>
                        <th scope="col">Nomor Identitas</th>
                        <th scope="col">Jam Booking</th>
                        <th scope="col">Jam Entry</th>
                        <th scope="col">Jam Check Out</th>
                        <th scope="col">Durasi Parkir</th>
                        <th scope="col">Harga Per Jam</th>
                        <th scope="col">No. Parkir</th>
                        <th scope="col">Hapus</th>
                        <th scope="col">Edit</th>
                    </tr>
                </thead>
                <tbody>
                     <?php foreach ($entry as $e) { ?>
                    <tr>
                        <td><?php echo $e['id_entry']; ?></td>
                        <td><?php echo $e['id_pelanggan']; ?></td>
                        <td><?php echo $e['id_booking']; ?></td>
                        <td><?php echo $e['nama']; ?></td>
                        <td><?php echo $e['nomor_plat']; ?></td>
                        <td><?php echo $e['no_identitas']; ?></td>
                        <td><?php 
                        if($e['id_booking'] != null){
                            echo $e['jam_booking'];
                        }else{
                            echo "Tidak ada booking";
                        } 
                        ?></td>
                        <td><?php echo $e['jam_entry']; ?></td>
                        <td><?php echo $e['jam_checkout']; ?></td>
                        <td><?php echo $e['durasi_entry']; ?></td>
                        <td><?php echo $e['harga_perjam']; ?></td>
                        <td><?php echo $e['no_parkir'];?></td>
                        <td><a href="<?= base_url(); ?>entry/hapus/<?= $e['id_entry']; ?>" class="badge badge-danger float-right" onclick="return confirm('Yakin Data ini akan dihapus');">Hapus</a></td>
                        <td><a href="<?= base_url(); ?>entry/edit/<?= $e['id_entry']; ?>" class="badge badge-success float-right">Edit</a></td>
                    </tr>
                <?php } ?>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</div>