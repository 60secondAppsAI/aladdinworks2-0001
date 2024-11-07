import http from "../http-common"; 

class PowerStripService {
  getAllPowerStrips(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/powerStrip/powerStrips`, searchDTO);
  }

  get(powerStripId) {
    return this.getRequest(`/powerStrip/${powerStripId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/powerStrip?field=${matchData}`, null);
  }

  addPowerStrip(data) {
    return http.post("/powerStrip/addPowerStrip", data);
  }

  update(data) {
  	return http.post("/powerStrip/updatePowerStrip", data);
  }
  
  uploadImage(data,powerStripId) {
  	return http.postForm("/powerStrip/uploadImage/"+powerStripId, data);
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

export default new PowerStripService();
