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
        $noParkir = $this->post('no_parkir');
        $data = [
            'id_pelanggan' => $this->post('id_pelanggan'),
            'jam_booking' => $this->post('jam_booking')
        ];
        if($noParkir != null && $data != null){
            $idParkir = $this->parkir->getIDParkirByNoParkir($noParkir);
            
            foreach ($idParkir as $id_parkir) {
                $data['id_parkir'] = $id_parkir['id_parkir'];
            }
            
            $inputBooking = $this->booking->inputBookingData($data);
            if($inputBooking > 0){
                $this->response(array('status'=>true,'message'=>'input booking berhasil'),RestController::HTTP_OK);
            }else{
                $this->response(array('status'=>false,'message'=>'input booking gagal!'),RestController::HTTP_OK);

            }
        }else{
            $this->response(array('status'=>false,'message'=>'inputan kosong!'),RestController::HTTP_OK);

        }
    }

}

/* End of file Booking_Api.php */


?>