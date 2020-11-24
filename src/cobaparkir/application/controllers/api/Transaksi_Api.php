<?php 

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;
use chriskacerguis\RestServer\Format;

class Transaksi_Api extends RestController {

    public function __construct(){
        parent::__construct();
        $this->load->model('transaksi_model','transaksi');
        
    }

    public function riwayat_post()
    {
        $idPelanggan = $this->post('id_pelanggan');
        if($idPelanggan != null){
            $getRiwayat = $this->transaksi->getTransaksiByIDPelanggan($idPelanggan);
            if($getRiwayat){
                $this->response(array('status'=>true,'data'=>$getRiwayat),RestController::HTTP_OK);
            }else{
                $this->response(array('status'=>false,'message'=>'data tidak ada!'),RestController::HTTP_OK);
            }
        }else{
            $this->response(array('status'=>true,'message'=>'belum ada inputan!'),RestController::HTTP_OK);
        }
    }

    public function inputtransaksi_post(){

    }

}

/* End of file Transaksi_Api.php */

?>