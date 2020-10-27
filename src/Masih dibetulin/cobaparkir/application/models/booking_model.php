<?php

class booking_model extends CI_Model {

    public function getAllBooking()
    {
        // $query = $this->db->get('booking');
        // return $query->result_array();

        $this->db->select('*');
        $this->db->from('booking b');
        $this->db->join('pelanggan p','b.id_pelanggan = b.id_booking');
        $query = $this->db->get();
        return $query->result_array();
    }

    public function tambahDataBooking()
    {
        $data = [
            "id_booking" => $this->input->post('id_booking', true),
            "id_pelanggan" => $this->input->post('id_pelanggan', true),
            "no_parkir" => $this->input->post('no_parkir', true),
            "jam_booking" => $this->input->post('jam_booking', true)
        ];
        $this->db->insert('booking', $data);
    }

    public function hapusDataBooking($id)
    {
        $this->db->where('id_booking', $id);
        $this->db->delete('booking');
    }

    public function getBookingByID($id)
    {
        return $this->db->get_where('booking', ['id_booking' => $id])->row_array();
    }

    public function editDataBooking()
    {
        $data = [
            "id_booking" => $this->input->post('id_booking', true),
            "id_pelanggan" => $this->input->post('id_pelanggan', true),
            "no_parkir" => $this->input->post('no_parkir', true),
            "jam_booking" => $this->input->post('jam_booking', true)
        ];
        $this->db->where('id_booking', $this->input->post('id_booking'));
        $this->db->update('booking', $data);
    }

    public function cariDataBooking()
    {
        $keyword = $this->input->post('keyword');
        $this->db->like('id_booking', $keyword);
        return $this->db->get('booking')->result_array();
    }
}

?>