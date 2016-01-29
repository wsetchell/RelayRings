import SimpleHTTPServer
import SocketServer
import os
from pi_sound import ReleSounds

PORT_NUMBER = 8000

import BaseHTTPServer


class MyHandler(BaseHTTPServer.BaseHTTPRequestHandler):
  def do_HEAD(s):
    s.send_response(200)
    s.send_header("Content-type", "text/html")
    s.end_headers()

    def do_GET(s):
      """Respond to a GET request."""
      print 'Got a request, ringing now'
      ReleSounds.ring()
      s.send_response(200)
      s.send_header("Content-type", "text/html")
      s.end_headers()
      s.wfile.write("<html><head><title>It worked.</title></head>")
      s.wfile.write("<p>You accessed path: %s</p>" % s.path)
      s.wfile.write("</body></html>")


      def main():
        print "Setting up"
        ReleSounds.ready()
        server_class = BaseHTTPServer.HTTPServer
        httpd = server_class(('', PORT_NUMBER), MyHandler)
        try:
          httpd.serve_forever()
        except KeyboardInterrupt:
          pass
          httpd.server_close()

          if __name__ == '__main__':
            main()
