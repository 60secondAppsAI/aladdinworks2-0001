import http from "../http-common"; 

class PowerSupplyService {
  getAllPowerSupplys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/powerSupply/powerSupplys`, searchDTO);
  }

  get(powerSupplyId) {
    return this.getRequest(`/powerSupply/${powerSupplyId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/powerSupply?field=${matchData}`, null);
  }

  addPowerSupply(data) {
    return http.post("/powerSupply/addPowerSupply", data);
  }

  update(data) {
  	return http.post("/powerSupply/updatePowerSupply", data);
  }
  
  uploadImage(data,powerSupplyId) {
  	return http.postForm("/powerSupply/uploadImage/"+powerSupplyId, data);
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

export default new PowerSupplyService();
