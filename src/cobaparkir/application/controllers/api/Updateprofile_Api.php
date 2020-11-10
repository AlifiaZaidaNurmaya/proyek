<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;
use chriskacerguis\RestServer\Format;

class UpdateProfile_Api extends RestController {

    public function __construct(){
        parent::__construct();
        $this->load->model('pelanggan_model','pelanggan');
        $this->load->model('petugas_model','petugas');
    }

    public function auth_post(){
        
        $id_pengguna = $this->post('id_pengguna');

        $data = [
            'nama' => $this->post('nama'),
            'password' => $this->post('password'),
            'alamat' => $this->post('alamat'),
            'nomor_plat' => $this->post('nomor_plat'),
            'nomor_telepon' => $this->post('nomor_telepon'),
            'no_identitas' => $this->post('no_identitas'),
            'email' => $this->post('email'),
            'huruf_acak' => $this->post('huruf_acak')
        ];

        if($this->pelanggan->UpdatePelanggan($data, $id_pengguna) > 0){
            
            $getPelanggan = $this->pelanggan->getPelangganByID($id_pengguna);

            if($getPelanggan){
                $this->response(array('status' => true,'role'=>'pelanggan','data'=>$getPelanggan, 'message'=>'Edit Profile Success'), RestController::HTTP_OK);
            }else{
                $this->response(array('status' => false,'message'=>'data tidak ada!'), RestController::HTTP_OK);
            }

            
        }else if($this->petugas->UpdatePetugas($data, $id_pengguna) > 0){
            $getPetugas = $this->petugas->getPetugasByID($id_pengguna);

            if($getPetugas){
                $this->response(array('status' => true,'role'=>'petugas','data'=>$getPetugas, 'message'=>'Edit Profile Success'), RestController::HTTP_OK);
            }else{
                $this->response(array('status' => false,'message'=>'data tidak ada!'), RestController::HTTP_OK);
            }
            
        }else{
            $this->response(array('status' => false,'message'=>'Edit Profile Error'), RestController::HTTP_OK);
        }
    }
}
?>