<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Signup_Api extends RestController {

    public function __construct(){
        parent::__construct();
        $this->load->model('pelanggan_model','pelanggan');
    }

    public function auth_post(){
        
        $data = [
            $nama = $this->post('nama'),
            $username = $this->post('username'),
            $password = $this->post('password'),
            $alamat = $this->post('alamat'),
            $nomor_plat = $this->post('nomor_plat'),
            $nomor_telepon = $this->post('nomor_telepon'),
            $no_identitas = $this->post('no_identitas'),
            $email = $this->post('email'),
            $huruf_acak = $this->post('huruf_acak')
        ];

            if($data[0] != null  && $data[2] != null && $data[4] != null && $data[5] != null && $data[6] != null && $data[7] != null && $data[8] != null){
                $cekSignup = $this->pelanggan->tambahDataPelanggan();
    
                if($cekSignup > 0){
                    $this->response(array('status' => true,'message'=>'Sign Up Success'), RestController::HTTP_OK);
                }else{
                    $this->response(array('status' => false,'message'=>'Sign Up Error'), RestController::HTTP_OK);
                }
            }else{
                $this->response(array('status' => false,'message'=>'Input masih kosong!'), RestController::HTTP_OK);
            }
        
        
    }
}
?>