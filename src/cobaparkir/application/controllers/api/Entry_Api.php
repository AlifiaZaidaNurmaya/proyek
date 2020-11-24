<?php
defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;
use chriskacerguis\RestServer\Format;

class Entry_Api extends RestController {


    public function __construct(){
        parent::__construct();
        $this->load->model('booking_model','booking');
        $this->load->model('tempatparkir_model','parkir');
        $this->load->model('entry_model','entry');
        $this->load->model('pelanggan_model','pelanggan');
        
    }

    // public function entry_post()
    // {
    //     $hurufAcak = $this->post('huruf_acak');

    //     if($hurufAcak != null){
    //         $cekEntry = $this->entry->getEntryByHurufAcak($hurufAcak);
            
    //         foreach($cekEntry as $ce){
    //             $idEntry = $ce['id_entry'];
    //             $hargaPerjam = $ce['harga_perjam'];
    //             $jamIn = $ce['jam_entry'];
    //             $idBooking = $ce['id_booking'];
    //             $idParkir = $ce['id_parkir'];
    //         }

    //         if($idEntry == null){
    //             $idBooking = $this->booking->getBookingByHurufAcak($hurufAcak);
    //             $idPelanggan = $this->pelanggan->getPelangganIDByHurufAcak($hurufAcak);
                
    //             foreach($idPelanggan as $idp){
    //                 $id_pelanggan = $idp['id_pelanggan'];
    //             }
                
    //             if($idBooking){

    //                 foreach($idBooking as $idb){
    //                     $id_booking = $idb['id_booking'];
    //                     $id_parkir = $idb['idparkir'];
    //                 }

    //                 // foreach($idPelanggan as $idp){
    //                 //     $id_pelanggan = $idp['id_pelanggan'];
    //                 // }

    //                 // foreach($id_parkir as $idp){
    //                 //     $idParkir = $idp['id_parkir'];
    //                 // }
                    
    //                 $data = [
    //                     'harga_perjam' => $this->post('harga_perjam'),
    //                     'durasi_entry' => NULL,
    //                     'id_pelanggan' => $id_pelanggan,
    //                     'id_booking' => $id_booking,
    //                     'id_parkir' => $id_parkir,
    //                     'jam_entry' => $this->post('jam_entry')
    //                 ];

    //                 $inputEntry = $this->entry->tambahDataEntry($data);
    //                 if($inputEntry > 0){
    //                     $booked['is_booked'] = 1;

    //                     $hapusBooking = $this->booking->deleteBookingByIDPelanggan($id_pelanggan);
    //                     $changeStatus = $this->parkir->updateIsBooked($id_parkir, $booked);

    //                     $this->response(array('status'=>true,'scan'=>'check in','message'=>'input entry berhasil!','data'=>$data), RestController::HTTP_OK);
    //                 }else{
    //                     $this->response(array('status'=>false,'message'=>'input entry gagal!'), RestController::HTTP_OK);
    //                 }


    //             }else{

    //                 $noParkir = $this->post('no_parkir');
    //                 $dataParkir = $this->parkir->getIDParkirByNoParkir($noParkir);

    //                 foreach($dataParkir as $dtp){
    //                     $idParkir = $dtp['id_parkir'];
    //                 }

    //                 $dataPelanggan = $this->pelanggan->getPelangganIDByHurufAcak($hurufAcak);
                    
    //                 foreach($dataPelanggan as $dtpl){
    //                     $idPelanggan = $dtpl['id_pelanggan'];
    //                 }

    //                 $data = [
    //                     'harga_perjam' => $this->post('harga_perjam'),
    //                     'durasi_entry' => NULL,
    //                     'id_pelanggan' => $idPelanggan,
    //                     'id_parkir' => $idParkir,
    //                     'jam_entry' => $this->post('jam_entry'),
    //                 ];

    //                 $inputEntry = $this->entry->tambahDataEntry($data);

    //                 if($inputEntry > 0){
    //                     $booked['is_booked'] = 1;
    //                     $changeStatus = $this->parkir->updateIsBooked($idParkir, $booked);

    //                     $this->response(array('status'=>true,'scan'=>'check in','message'=>'input entry berhasil!','data'=>$data), RestController::HTTP_OK);
    //                 }else{
    //                     $this->response(array('status'=>false,'message'=>'input entry gagal!'), RestController::HTTP_OK);
    //                 }

    //             }
    //         }else{

    //             $jamCheckOut = $this->post('jam_checkout');
    //             $dataPelanggan = $this->pelanggan->getPelangganIDByHurufAcak($hurufAcak);

    //             foreach($dataPelanggan as $dtpl){
    //                 $idPelanggan = $dtpl['id_pelanggan'];
    //             }

    //             $data = [
    //                 'id_entry'=>$idEntry,
    //                 'harga_perjam'=>$hargaPerjam,
    //                 'jam_entry'=>$jamIn,
    //                 'jam_checkout'=>$jamCheckOut,
    //                 'id_pelanggan'=>$idPelanggan,
    //                 'id_booking'=>$idBooking,
    //                 'id_parkir' => $idParkir
    //             ];

    //             $hapusEntry = $this->entry->deleteDataEntryByID($idEntry);
    //             if($hapusEntry > 0){
    //                 $booked['is_booked'] = 0;
    //                 $changeStatus = $this->parkir->updateIsBooked($idParkir, $booked);
    //                 $this->response(array('status'=>true,'scan'=>'check out','message'=>'semoga perjalanan anda menyenangkan','data'=>$data), RestController::HTTP_OK);
    //             }
    //         }
            
    //     }else{
    //         $this->response(array('status'=>false,'message'=>'huruf acak tidak ada!'));
    //     }
    // }

    public function checkout_post()
    {
        $hurufAcak = $this->post('huruf_acak');

        if($hurufAcak != null){
            $cekEntry = $this->entry->getEntryByHurufAcak($hurufAcak);
            
            foreach($cekEntry as $ce){
                $idEntry = $ce['id_entry'];
                $hargaPerjam = $ce['harga_perjam'];
                $jamIn = $ce['jam_entry'];
                $idBooking = $ce['id_booking'];
                $idParkir = $ce['id_parkir'];
            }

                $jamCheckOut = $this->post('jam_checkout');
                $dataPelanggan = $this->pelanggan->getPelangganIDByHurufAcak($hurufAcak);

                foreach($dataPelanggan as $dtpl){
                    $idPelanggan = $dtpl['id_pelanggan'];
                }

                $data = [
                    'id_entry'=>$idEntry,
                    'harga_perjam'=>$hargaPerjam,
                    'jam_entry'=>$jamIn,
                    'jam_checkout'=>$jamCheckOut,
                    'id_pelanggan'=>$idPelanggan,
                    'id_booking'=>$idBooking,
                    'id_parkir' => $idParkir
                ];

                $hapusEntry = $this->entry->deleteDataEntryByID($idEntry);
                if($hapusEntry > 0){
                    $booked['is_booked'] = 0;
                    $changeStatus = $this->parkir->updateIsBooked($idParkir, $booked);
                    $this->response(array('status'=>true,'scan'=>'check out','message'=>'semoga perjalanan anda menyenangkan','data'=>array($data)), RestController::HTTP_OK);
                }else{
                    $this->response(array('status'=>false,'message'=>'hapus entry gagal'), RestController::HTTP_OK);
                }
            
        }else{
            $this->response(array('status'=>false,'message'=>'huruf acak tidak ada!'));
        }
    }


    public function entrybooking_post()
    {
        $hurufAcak = $this->post('huruf_acak');
        $idBooking = $this->booking->getBookingByHurufAcak($hurufAcak);
        $idPelanggan = $this->pelanggan->getPelangganIDByHurufAcak($hurufAcak);
        
            foreach($idPelanggan as $idp){
                $id_pelanggan = $idp['id_pelanggan'];
            }

            foreach($idBooking as $idb){
                $id_booking = $idb['id_booking'];
                $id_parkir = $idb['idparkir'];
            }
            
            $data = [
                'harga_perjam' => $this->post('harga_perjam'),
                'durasi_entry' => NULL,
                'id_pelanggan' => $id_pelanggan,
                'id_booking' => $id_booking,
                'id_parkir' => $id_parkir,
                'jam_entry' => $this->post('jam_entry')
            ];

            $inputEntry = $this->entry->tambahDataEntry($data);
            if($inputEntry > 0){
                $booked['is_booked'] = 1;

                $hapusBooking = $this->booking->deleteBookingByIDPelanggan($id_pelanggan);
                $changeStatus = $this->parkir->updateIsBooked($id_parkir, $booked);

                $this->response(array('status'=>true,'scan'=>'check in','message'=>'input entry berhasil!','data'=>$data), RestController::HTTP_OK);
            }else{
                $this->response(array('status'=>false,'message'=>'input entry gagal!'), RestController::HTTP_OK);
            }
    }

    public function cekentry_post(){
        $hurufAcak = $this->post('huruf_acak');

        if($hurufAcak != null){
            $cekEntry = $this->entry->getEntryByHurufAcak($hurufAcak);
            
            foreach($cekEntry as $ce){
                $idEntry = $ce['id_entry'];
                $hargaPerjam = $ce['harga_perjam'];
                $jamIn = $ce['jam_entry'];
                $idBooking = $ce['id_booking'];
                $idParkir = $ce['id_parkir'];
            }

            if(!$cekEntry){
                $this->response(array('status'=>true,'data'=>$cekEntry,'scan'=>'check in'), RestController::HTTP_OK);
            }else{
                $this->response(array('status'=>true,'data'=>$cekEntry,'scan'=>'check out'), RestController::HTTP_OK);
            }
            
        }else{
            $this->response(array('status'=>false,'message'=>'huruf acak tidak ada!'), RestController::HTTP_OK);
        }
    }

    public function cekentrypelanggan_post(){
        $hurufAcak = $this->post('huruf_acak');

        if($hurufAcak != null){
            $cekEntry = $this->entry->getEntryByHurufAcak($hurufAcak);
            
            foreach($cekEntry as $ce){
                $idEntry = $ce['id_entry'];
                $hargaPerjam = $ce['harga_perjam'];
                $jamIn = $ce['jam_entry'];
                $idBooking = $ce['id_booking'];
                $idParkir = $ce['id_parkir'];
            }

            if($idEntry == null){
                $this->response(array('status'=>false,'message'=>'anda belum melakukan check in'), RestController::HTTP_OK);
            }else{
                $data = [
                    'harga_perjam' => $hargaPerjam,
                    'durasi_entry' => NULL,
                    'id_pelanggan' => $id_pelanggan,
                    'id_booking' => $id_booking,
                    'id_parkir' => $id_parkir,
                    'jam_entry' => $jamIn
                ];
                $this->response(array('status'=>true,'message'=>'Selamat datang!','data'=>array($data)), RestController::HTTP_OK);
                
            }
            
        }else{
            $this->response(array('status'=>false,'message'=>'huruf acak tidak ada!'), RestController::HTTP_OK);
        }
    }


    public function entrynobooking_post(){
        $hurufAcak = $this->post('huruf_acak');
        $noParkir = $this->post('no_parkir');
        $dataParkir = $this->parkir->getIDParkirByNoParkir($noParkir);

        foreach($dataParkir as $dtp){
            $idParkir = $dtp['id_parkir'];
        }

        $dataPelanggan = $this->pelanggan->getPelangganIDByHurufAcak($hurufAcak);
        
        foreach($dataPelanggan as $dtpl){
            $idPelanggan = $dtpl['id_pelanggan'];
        }

        $data = [
            'harga_perjam' => $this->post('harga_perjam'),
            'durasi_entry' => NULL,
            'id_pelanggan' => $idPelanggan,
            'id_parkir' => $idParkir,
            'jam_entry' => $this->post('jam_entry'),
        ];

        $inputEntry = $this->entry->tambahDataEntry($data);

        if($inputEntry > 0){
            $booked['is_booked'] = 1;
            $changeStatus = $this->parkir->updateIsBooked($idParkir, $booked);

            $this->response(array('status'=>true,'scan'=>'check in','message'=>'input entry berhasil!','data'=>array($data)), RestController::HTTP_OK);
        }else{
            $this->response(array('status'=>false,'message'=>'input entry gagal!'), RestController::HTTP_OK);
        }

   
    }

}



/* End of file Entry_Api.php */


?>