<?php 
defined('BASEPATH') OR exit('No direct script access allowed');

class tempatparkir_model extends CI_Model {

    public function getAllParkir(){
        $query = $this->db->get('tempat_parkir');
        return $query->result_array();
        
    }

    public function getIDParkirByNoParkir($noParkir){
        $this->db->select('id_parkir, is_booked');
        $this->db->from('tempat_parkir');
        
        $this->db->where('no_parkir',$noParkir);
        return $this->db->get()->result_array();
    }

    public function updateIsBookedByNoParkir($noParkir,$data){
        
        $this->db->where('no_parkir', $noParkir);
        $this->db->update('tempat_parkir', $data);
        return $this->db->affected_rows();

        
    }

}

/* End of file tempatparkir_model.php */