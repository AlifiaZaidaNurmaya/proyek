<?php 

defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;
use chriskacerguis\RestServer\Format;

class Transaksi_Api extends CI_Controller {

    public function __construct(){
        parent::__construct();
        $this->load->model('transaksi_model','transaksi');
        
    }

    public function transaksi_post()
    {
        
    }

}

/* End of file Transaksi_Api.php */

?>