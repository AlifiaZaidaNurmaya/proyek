<?php

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;
use chriskacerguis\RestServer\Format;


class Booking_Api extends RestController {

    public function __construct(){
        parent::__construct();
        $this->load->model('booking_model','booking');
        
    }

    public function index_post()
    {
        $data = [
            'id_pelanggan' => $this->post('id_pelanggan'),
            '' => $this->post('')
        ];
    }

}

/* End of file Booking_Api.php */


?>