<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Home extends CI_Controller {

    
    public function index()
    {
        $data['title']='Home';
        // $data adalah sebuah array dengan isi arraynya adalah nama dan diisi $name
        // $data['name']=$name;
        $this->load->view('template/header_dash', $data);
        //tambahkan $data pada home/index
        $this->load->view('home/index');
        //$this->load->view('template/footer');
        $this->load->view('template/footer_dash');

    }

}

/* End of file Controllername.php */

?>