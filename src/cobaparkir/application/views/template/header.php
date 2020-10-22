<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--<title>Hello, world!</title>-->
    <title><?= $title ?></title>
  <!-- style css -->
  <style>
  .badge{
    margin-left:3px;
  }
  </style>

  <!-- <title>Hello, world</title> -->
  <title><?= $title ?></title>
  </head>
  <body>

<!-- navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
  <a class="navbar-brand" href="#">Parkir</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link" href="<?= base_url(); ?>user">Data Pelanggan</a>
      <a class="nav-item nav-link" href="<?= base_url(); ?>petugas">Data Petugas</a>
      <a class="nav-item nav-link" href="<?= base_url(); ?>superadmin">Data Pegawai Super Admin</a>
      <!-- <a class="nav-item nav-link disabled" href="#">Disabled</a> -->
      </div>
    </div>
  </div>
</nav>