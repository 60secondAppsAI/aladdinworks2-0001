import http from "../http-common"; 

class ServerService {
  getAllServers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/server/servers`, searchDTO);
  }

  get(serverId) {
    return this.getRequest(`/server/${serverId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/server?field=${matchData}`, null);
  }

  addServer(data) {
    return http.post("/server/addServer", data);
  }

  update(data) {
  	return http.post("/server/updateServer", data);
  }
  
  uploadImage(data,serverId) {
  	return http.postForm("/server/uploadImage/"+serverId, data);
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

export default new ServerService();
