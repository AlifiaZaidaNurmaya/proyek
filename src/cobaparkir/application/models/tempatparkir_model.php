<?php 
defined('BASEPATH') OR exit('No direct script access allowed');

class tempatparkir_model extends CI_Model {

    public function getAllParkir(){
        $query = $this->db->get('tempat_parkir');
        return $query->result_array();
        
    }

}

/* End of file tempatparkir_model.php */