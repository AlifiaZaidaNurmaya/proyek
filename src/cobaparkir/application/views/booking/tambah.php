<div class="container" style="margin-left:300px;">
    <div class="row mt-3">
        <div class="col-md-6">
            <!-- http://getbootstrap.com/docs/4.1/components/card/ -->
            <div class="card">
                <div class="card-header">
                    Form Tambah Data Booking
                </div>
                <div class="card-body">
                <!-- Untuk menampilkan pesan error -->
                <?php if (validation_errors()):?>
                <div class="alert alert-danger" role="alert">
                    <?= validation_errors(); ?>
                </div>
                <?php endif ?>
                    <form action="" method="post" enctype="multipart/form-data">
                    <!-- http://getbootstrap.com/docs/4.1/components/card/forms/ -->
                    <div class="form-group">
                        <label for="id_pelanggan">Nama Pelanggan</label>
                        <select name="id_pelanggan" id="id_pelanggan" class="form-control">
                            <?php foreach ($pelanggan as $p) :?>
                            <option value="<?= $p['id_pelanggan'];?>">
                                <?= $p['nama']; ?>    
                            </option>
                            <?php endforeach;?>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="no_parkir">Nomor Parkir</label>
                        <select class="form-control" id="id_parkir" name="id_parkir">
                            <?php foreach ($booking as $b) :?>
                                <?php foreach ($parkir as $pk) :?>
                                    <?php if($pk['no_parkir'] != $b['no_parkir']) :?>
                                        <option value="<?= $pk['id_parkir'];?>">
                                            <?= $pk['no_parkir']; ?>    
                                        </option>
                                    <?php endif;?>
                                <?php endforeach;?>
                            <?php endforeach;?>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="jam_booking">Jam Booking</label>
                        <input type="text" class="form-control" id="jam_booking" name="jam_booking" value="<?= date('Y-m-d H:i:s'); ?>" readonly>
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary float-right" > Submit </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>