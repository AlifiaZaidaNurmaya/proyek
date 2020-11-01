<div class="container" style="margin-left:300px;">
    <div class="row mt-3">
        <div class="col-md-6">
            <!-- http://getbootstrap.com/docs/4.1/components/card/ -->
            <div class="card">
                <div class="card-header">
                    Form Edit Data Entry
                </div>
                <div class="card-body">
                <!-- Untuk menampilkan pesan error -->
                <?php if (validation_errors()):?>
                <div class="alert alert-danger" role="alert">
                    <?= validation_errors(); ?>
                </div>
                <?php endif ?>
                    <form action="" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="<?= $entry['id_entry'];?>">
                    <!-- http://getbootstrap.com/docs/4.1/components/card/forms/ -->
                    <div class="form-group">
                        <label for="id_entry">ID Entry</label>
                        <input type="text"
                            class="form-control"
                            id="id_entry"
                            name="id_entry"
                        value="<?= $entry['id_entry'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="id_pelanggan">ID Pelanggan</label>
                        <input type="text"
                            class="form-control"
                            id="id_pelanggan"
                            name="id_pelanggan"
                        value="<?= $entry['id_pelanggan'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="id_booking">ID Booking</label>
                        <input type="text"
                            class="form-control"
                            id="id_booking"
                            name="id_booking"
                        value="<?= $entry['id_booking'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="id_pelanggan">Nama Pelanggan</label>
                        <input type="int"
                            class="form-control"
                            id="id_pelanggan"
                            name="id_pelanggan"
                        value="<?= $entry['nama'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="id_pelanggan">Nomor Plat</label>
                        <input type="text"
                            class="form-control"
                            id="nomor_plat"
                            name="nomor_plat"
                        value="<?= $entry['nomor_plat'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="no_parkir">No. Parkir</label>
                        <input type="text"
                            class="form-control"
                            id="id_parkir"
                            name="id_parkir"
                        value="<?= $booking['no_parkir'];?>">
                    </div>
                    <div class="form-group">
                        <label for="jam_booking">Jam Booking</label>
                        <input type="text"
                            class="form-control"
                            id="jam_booking"
                            name="jam_booking"
                        value="<?= $booking['jam_booking'];?>" readonly>
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary float-right" > Edit </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>