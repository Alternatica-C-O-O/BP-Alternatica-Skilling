output "supabase_project_ref_imported" {
  description = "The reference ID of the imported Supabase project."
  value       = supabase_project.production.id
}

output "supabase_project_name" {
  description = "The name of the imported Supabase project."
  value       = supabase_project.production.name
}

output "database_connection_string" {
  description = "JDBC connection string for your PostgreSQL database (sensitive)."
  value       = "jdbc:postgresql://db.jtnsigjcpxhkxhpbotkv.supabase.co:5432/postgres?user=postgres&password=alternatica-skilling&sslmode=require"
  sensitive   = true
}