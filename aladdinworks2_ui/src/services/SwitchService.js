import http from "../http-common"; 

class SwitchService {
  getAllSwitchs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/switch/switchs`, searchDTO);
  }

  get(switchId) {
    return this.getRequest(`/switch/${switchId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/switch?field=${matchData}`, null);
  }

  addSwitch(data) {
    return http.post("/switch/addSwitch", data);
  }

  update(data) {
  	return http.post("/switch/updateSwitch", data);
  }
  
  uploadImage(data,switchId) {
  	return http.postForm("/switch/uploadImage/"+switchId, data);
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

export default new SwitchService();
