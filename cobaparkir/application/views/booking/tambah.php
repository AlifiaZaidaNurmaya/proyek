<div class="container">
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
                        <label for="id_booking">ID Pelanggan</label>
                        <input type="int" class="form-control" id="id_booking" name="id_booking">
                    </div>
                    <div class="form-group">
                        <label for="id_pelanggan">ID Pelanggan</label>
                        <input type="int" class="form-control" id="id_pelanggan" name="id_pelanggan">
                    </div>
                    <div class="form-group">
                        <label for="no_parkir">No. Parkir</label>
                        <input type="text" class="form-control" id="no_parkir" name="no_parkir">
                    </div>
                    <div class="form-group">
                        <label for="jam_booking">Jam Booking</label>
                        <input type="text" class="form-control" id="jam_booking" name="jam_booking">
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary float-right" > Submit </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>