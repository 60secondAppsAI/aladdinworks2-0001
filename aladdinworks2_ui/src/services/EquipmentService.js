import http from "../http-common"; 

class EquipmentService {
  getAllEquipments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/equipment/equipments`, searchDTO);
  }

  get(equipmentId) {
    return this.getRequest(`/equipment/${equipmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/equipment?field=${matchData}`, null);
  }

  addEquipment(data) {
    return http.post("/equipment/addEquipment", data);
  }

  update(data) {
  	return http.post("/equipment/updateEquipment", data);
  }
  
  uploadImage(data,equipmentId) {
  	return http.postForm("/equipment/uploadImage/"+equipmentId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new EquipmentService();
