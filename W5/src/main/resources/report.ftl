<!DOCTYPE html>
<html>
<head>
    <title>Image Repository Report</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .timestamp { margin-top: 20px; font-size: 0.9em; color: #666; }
    </style>
</head>
<body>
    <h1>Image Repository Report</h1>
    <p>Total images: ${images?size}</p>

    <table>
        <tr>
            <th>Name</th>
            <th>Date</th>
            <th>Tags</th>
            <th>File Path</th>
        </tr>
        <#list images as image>
        <tr>
            <td>${image.name()}</td>
            <td>${image.date()}</td>
            <td>${image.tags()?join(", ")}</td>
            <td>${image.filePath()}</td>
        </tr>
        </#list>
    </table>

    <div class="timestamp">Report generated: ${timestamp?datetime}</div>
</body>
</html>