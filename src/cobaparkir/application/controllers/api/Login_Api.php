<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;
use chriskacerguis\RestServer\Format;

class Login_Api extends RestController {

    public function __construct(){
        parent::__construct();
        $this->load->model('Login_model','login');
        
    }


    public function auth_post()
    {
        $email = $this->post('email');
        $password = $this->post('password');
        
        if($email != null && $password != null){
            // $this->rest->format('application/x-www-form-urlencoded');
            $cekLogin = $this->login->login_pelanggan($email, $password);
            $cekPetugas = $this->login->login_petugas($email,$password);

            if($cekLogin){
                $this->response(array('status' => true,'role'=>'pelanggan','data'=>$cekLogin), RestController::HTTP_OK);
            }else{
                if($cekPetugas){
                    $this->response(array('status' => true,'role'=>'petugas','data'=>$cekPetugas), RestController::HTTP_OK);
                }else{
                    $this->response(array('status' => false,'message'=>'data tidak ada!'), RestController::HTTP_OK);
                }
            }

        }else{
            $this->response(array('status' => false,'message'=>'email dan/atau password kosong'), RestController::HTTP_OK);
        }
        
    }

}

/* End of file Login_Api.php */

?>