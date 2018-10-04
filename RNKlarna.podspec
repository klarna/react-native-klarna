require 'json'
package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name           = 'react-native-klarna'
  s.version        = package['version']
  s.summary        = package['description']
  s.description    = package['description']
  s.license        = package['license']
  s.author         = package['author']
  s.homepage       = package['homepage']

  s.platform     = :ios, "9.0"
  s.source = { :git => 'https://github.com/dov11/react-native-klarna', :tag => s.version }
  s.source_files  = "ios/**/*.{h,m}"
  s.requires_arc = true

  s.preserve_paths = 'LICENSE', 'README.md', 'package.json', 'index.js'

  s.dependency "React"
  s.dependency "KlarnaCheckoutSDK"

end

  