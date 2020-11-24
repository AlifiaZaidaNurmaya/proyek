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
                $getBookingData = $this->booking->getBookingByIDPelangganAndroid($data['id_pelanggan']);
                $this->response(array('status'=>true,'data'=>$getBookingData,'message'=>'input booking berhasil'),RestController::HTTP_OK);
            }else{
                $this->response(array('status'=>false,'message'=>'input booking gagal!'),RestController::HTTP_OK);

            }
        }else{
            $this->response(array('status'=>false,'message'=>'inputan kosong!'),RestController::HTTP_OK);

        }
    }

    public function delete_post(){
        $idPelanggan = $this->post('id_pelanggan');
        if($idPelanggan != null){
            $data = $this->booking->getBookingByIDPelangganAndroid($idPelanggan);

            foreach($data as $dt){
                $noParkir = $dt['no_parkir'];
            }

            $hapusBooking = $this->booking->deleteBookingByIDPelanggan($idPelanggan);

            if($hapusBooking > 0){
                $data['is_booked'] = 0;
                
                $changeStatus = $this->parkir->updateIsBookedByNoParkir($noParkir, $data);
                $this->response(array('status'=>true,'message'=>'data booking pelanggan berhasil dihapus'),RestController::HTTP_OK);
            }else{
                $this->response(array('status'=>false,'message'=>'data booking pelanggan gagal dihapus!'),RestController::HTTP_OK);
            }
        }else{
            $this->response(array('status'=>false,'message'=>'input kosong!'),RestController::HTTP_OK);
        }
    }

    public function changestatus_post(){
        $noParkir = $this->post('no_parkir');
        
        if($noParkir != null){

            $dataBooked = $this->parkir->getIDParkirByNoParkir($noParkir);
            foreach($dataBooked as $db){
                $isBooked = $db['is_booked'];
            }

            if($isBooked == 0){

                $data = [
                    'is_booked' => 1
                ];
                $changeStatus = $this->parkir->updateIsBookedByNoParkir($noParkir, $data);
                if($changeStatus > 0){
                    $this->response(array('status'=>true,'message'=>'parkir berhasil update!'),RestController::HTTP_OK);
                }else{
                    $this->response(array('status'=>false,'message'=>'parkir gagal update!'),RestController::HTTP_OK);
                }
                
            }else{
                $this->response(array('status'=>false,'message'=>'parkir sudah dibooking!'),RestController::HTTP_OK);
            }
        }else{
            $this->response(array('status'=>false,'message'=>'inputan kosong!'),RestController::HTTP_OK);
        }
    }

    public function cekbookingpelanggan_post(){
        $hurufAcak = $this->post('huruf_acak');
        $idBooking = $this->booking->getBookingByHurufAcak($hurufAcak);
        
        if($idBooking){
            $this->response(array('status'=>true, 'data'=>$idBooking ,'booking'=>true), RestController::HTTP_OK);
        }else{
            $this->response(array('status'=>true,'data'=>$idBooking,'booking'=>false), RestController::HTTP_OK);
        }
    }

}

/* End of file Booking_Api.php */


?>