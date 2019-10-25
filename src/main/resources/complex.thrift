namespace java org.lyh.service

service ComplextService {

    string getFullName(1: string firstName; 2: string sceondName = "lyh";)
}