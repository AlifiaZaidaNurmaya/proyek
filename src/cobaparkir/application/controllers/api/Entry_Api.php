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
        
    }

    public function index()
    {
        
    }

}

/* End of file Entry_Api.php */


?>