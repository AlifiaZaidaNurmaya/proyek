<?php 
defined('BASEPATH') OR exit('No direct script access allowed');

class tempatparkir_model extends CI_Model {

    public function getAllParkir(){
        $query = $this->db->get('tempat_parkir');
        return $query->result_array();
        
    }

    public function getIDParkirByNoParkir($noParkir){
        $this->db->select('id_parkir');
        $this->db->from('tempat_parkir');
        
        $this->db->where('no_parkir',$noParkir);
        return $this->db->get()->result_array();
    }

}

/* End of file tempatparkir_model.php */