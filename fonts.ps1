$SCRIPT_DIR = Split-Path -Parent $MyInvocation.MyCommand.Definition
Set-Location $SCRIPT_DIR

Write-Host "> Se descargaran y procesaran las fuentes ..."

# --- FUENTE ROBOTO ---
Write-Host "> Descargando y procesando fuente Roboto ..."
$TempFontDirRoboto = "fonts_temp_roboto"
New-Item -ItemType Directory -Path $TempFontDirRoboto -Force
Invoke-WebRequest -Uri "https://mirrors.ctan.org/fonts/roboto.zip" -OutFile "$TempFontDirRoboto/roboto.zip"
Expand-Archive -Path "$TempFontDirRoboto/roboto.zip" -DestinationPath "$TempFontDirRoboto" -Force
$RobotoOpenTypePath = Join-Path $TempFontDirRoboto "roboto/opentype"
    
Write-Host "> Instalando fuentes Roboto ..."
$shell = New-Object -ComObject Shell.Application
$fontsFolder = $shell.Namespace(0x14)
Get-ChildItem -Path "$RobotoOpenTypePath" -Filter "*.otf" | ForEach-Object {
    Write-Host " Instalando $($_.Name)..."
    $fontsFolder.CopyHere($_.FullName, 0x0)
}

Remove-Item "$TempFontDirRoboto" -Recurse -Force
Remove-Item "roboto.zip" -ErrorAction SilentlyContinue


# --- FUENTE XCHARTER ---
Write-Host "> Descargando y procesando fuente XCharter"
$TempFontDirXCharter = "fonts_temp_xcharter"
New-Item -ItemType Directory -Path $TempFontDirXCharter -Force
Invoke-WebRequest -Uri "http://mirrors.ctan.org/fonts/xcharter.zip" -OutFile "$TempFontDirXCharter/xcharter.zip"
Expand-Archive -Path "$TempFontDirXCharter/xcharter.zip" -DestinationPath "$TempFontDirXCharter" -Force
$XCharterOpenTypePath = Join-Path $TempFontDirXCharter "xcharter/opentype"

Write-Host "> Instalando fuentes XCharter..."
Get-ChildItem -Path $XCharterOpenTypePath -Filter "*.otf" | ForEach-Object {
    Write-Host " Instalando $($_.Name)..."
    $fontsFolder.copyHere($_.FullName, 0x0)
}

Remove-Item "$TempFontDirXCharter" -Recurse -Force
Remove-Item "xcharter.zip" -ErrorAction SilentlyContinue

Write-Host "> Todas las fuentes se han descargado e instalado correctamente."