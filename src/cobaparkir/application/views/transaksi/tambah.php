<div class="container" style="margin-left:300px;">
    <div class="row mt-3">
        <div class="col-md-6">
            <!-- http://getbootstrap.com/docs/4.1/components/card/ -->
            <div class="card">
                <div class="card-header">
                    Form Tambah Data Transaksi
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
                        <label for="id_transaksi">ID Transaksi</label>
                        <input type="int" class="form-control" id="id_transaksi" name="id_transaksi">
                    </div>
                    <div class="form-group">
                        <label for="id_entry">ID Entry</label>
                        <select name="id_entry" id="id_entry" class="form-control">
                            <?php foreach ($entry as $e) :?>
                            <option value="<?= $e['id_entry'];?>">
                                <?= $e['id_entry']; ?>
                            </option>
                            <?php endforeach;?>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="jenis_transaksi">Jenis Transaksi</label>
                        <input type="text" class="form-control" id="jenis_transaksi" name="jenis_transaksi">
                    </div>
                    <div class="form-group">
                        <label for="jam_checkout">Jam Checkout</label>
                        <input type="text" class="form-control" id="jam_checkout" name="jam_checkout" value="<?= date('Y-m-d H:i:s'); ?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="total">Total</label>
                        <input type="int" class="form-control" id="total" name="total">
                    </div>
                    <div class="form-group">
                        <label for="id_petugas">ID Petugas</label>
                        <select name="id_petugas" id="id_petugas" class="form-control">
                            <?php foreach ($petugas as $pt) :?>
                            <option value="<?= $pt['id_petugas'];?>">
                                <?= $pt['id_petugas']; ?>
                            </option>
                            <?php endforeach;?>
                        </select>
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary float-right" > Submit </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>