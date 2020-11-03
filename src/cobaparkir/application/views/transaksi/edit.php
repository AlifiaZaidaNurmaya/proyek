<div class="container" style="margin-left:300px;">
    <div class="row mt-3">
        <div class="col-md-6">
            <!-- http://getbootstrap.com/docs/4.1/components/card/ -->
            <div class="card">
                <div class="card-header">
                    Form Edit Data Transaksi
                </div>
                <div class="card-body">
                <!-- Untuk menampilkan pesan error -->
                <?php if (validation_errors()):?>
                <div class="alert alert-danger" role="alert">
                    <?= validation_errors(); ?>
                </div>
                <?php endif ?>
                    <form action="" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="<?= $transaksi['id_transaksi'];?>">
                    <!-- http://getbootstrap.com/docs/4.1/components/card/forms/ -->
                    <div class="form-group">
                        <label for="id_transaksi">ID Transaksi</label>
                        <input type="text"
                            class="form-control"
                            id="id_transaksi"
                            name="id_transaksi"
                        value="<?= $transaksi['id_transaksi'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="id_entry">ID Entry</label>
                        <input type="int"
                            class="form-control"
                            id="id_entry"
                            name="id_entry"
                        value="<?= $transaksi['id_entry'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="jenis_transaksi">Jenis Transaksi</label>
                        <input type="text"
                            class="form-control"
                            id="jenis_transaksi"
                            name="jenis_transaksi"
                        value="<?= $transaksi['jenis_transaksi'];?>">
                    </div>
                    <div class="form-group">
                        <label for="jam_checkout">Jam Checkout</label>
                        <input type="text"
                            class="form-control"
                            id="jam_checkout"
                            name="jam_checkout"
                        value="<?= $transaksi['jam_checkout'];?>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="total">Total</label>
                        <input type="text"
                            class="form-control"
                            id="total"
                            name="total"
                        value="<?= $transaksi['total'];?>">
                    </div>
                    <div class="form-group">
                        <label for="id_petugas">ID Petugas</label>
                        <input type="text"
                            class="form-control"
                            id="id_petugas"
                            name="id_petugas"
                        value="<?= $transaksi['id_petugas'];?>" readonly>
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary float-right" > Edit </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>