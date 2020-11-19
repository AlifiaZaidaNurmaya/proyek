<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;
use chriskacerguis\RestServer\Format;


class Booking_Api extends RestController {

    public function __construct(){
        parent::__construct();
        $this->load->model('booking_model','booking');
        $this->load->model('tempatparkir_model','parkir');
        
    }

    public function cek_post()
    {
        // $cek = $this->post('cek');
        // if($cek != null){
            $cekParkir = $this->parkir->getAllParkir();
            if($cekParkir){
                $this->response(array('status' => true,'data'=>$cekParkir), RestController::HTTP_OK);
            }else{
                $this->response(array('status' => false,'message'=>'maaf data tidak ada!'), RestController::HTTP_OK);
            }
        // }else{
        //     $this->response(array('status' => false,'message'=>'maaf input kosong!'), RestController::HTTP_OK);
        // }
    }

    public function input_post(){
        $idPelanggan = $this->post('id_pelanggan');
        $noParkir = $this->post('no_parkir');
        $jamBooking = $this->post('jam_booking');
        
        if($idPelanggan != null && $noParkir != null && $jamBooking != null ){
            $idParkir = $this->parkir-> getIDParkirByNoParkir($noParkir);
            $inputBooking = $this->booking->inputBookingData($idParkir);
            if($inputBooking > 0){
                $this->response(array('status'=>true,),RestController::HTTP_OK);
            }
        }
    }

}

/* End of file Booking_Api.php */


?>