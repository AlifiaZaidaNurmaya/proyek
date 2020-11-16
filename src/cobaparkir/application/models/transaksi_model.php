<?php

class transaksi_model extends CI_Model {

    public function getAllTransaksi()
    {
        $this->db->select('*');
        $this->db->from('transaksi t');
        $this->db->join('entry e', 't.id_entry = e.id_entry');
        $this->db->join('petugas pt', 't.id_petugas = pt.id_petugas');
        $query = $this->db->get();
        return $query->result_array();
    }

    public function tambahDataTransaksi()
    {
        $data = [
            "id_transaksi" => $this->input->post('id_transaksi', true),
            "id_entry" => $this->input->post('id_entry', true),
            "jenis_transaksi" => $this->input->post('jenis_transaksi', true),
            "jam_checkout" => $this->input->post('jam_checkout', true),
            "total" => $this->input->post('total', true),
            "id_petugas" => $this->input->post('id_petugas', true)
        ];
        $this->db->insert('transaksi', $data);
    }

    public function hapusDataTransaksi($id)
    {
        $this->db->where('id_transaksi', $id);
        $this->db->delete('transaksi');
    }

    public function getTransaksiByID($id)
    {
        $this->db->select('*');
        $this->db->from('transaksi t');
        $this->db->join('entry e', 't.id_entry = e.id_entry');
        $this->db->join('petugas pt', 't.id_petugas = pt.id_petugas');
        $this->db->where('id_transaksi', $id);

        return $this->db->get()->row_array();
    }

    public function getTransaksiByIDPelanggan($idPelanggan)
    {
        $this->db->select('*');
        $this->db->from('transaksi t');
        $this->db->join('entry e', 't.id_entry = e.id_entry');
        $this->db->join('petugas pt', 't.id_petugas = pt.id_petugas');
        $this->db->where('e.id_pelanggan', $idPelanggan);

        return $this->db->get()->result_array();
    }
    

    public function editDataTransaksi()
    {
        $data = [
            "id_transaksi" => $this->input->post('id_transaksi', true),
            "id_entry" => $this->input->post('id_entry', true),
            "jenis_transaksi" => $this->input->post('jenis_transaksi', true),
            "jam_checkout" => $this->input->post('jam_checkout', true),
            "total" => $this->input->post('total', true),
            "id_petugas" => $this->input->post('id_petugas', true)
        ];
        $this->db->where('id_transaksi', $this->input->post('id_transaksi'));
        $this->db->update('transaksi', $data);
    }

    public function cariDataTransaksi()
    {
        $keyword = $this->input->post('keyword');
        $this->db->or_like('id_transaksi', $keyword);
        $this->db->or_like('e.id_entry', $keyword);
        $this->db->or_like('pt.id_pertugas', $keyword);
        $this->db->from('transaksi t');
        $this->db->join('entry e', 't.id_entry = e.id_entry');
        $this->db->join('petugas pt', 't.id_petugas = pt.id_petugas');
        $query = $this->db->get();
        return $query->result_array();
    }
}

?>