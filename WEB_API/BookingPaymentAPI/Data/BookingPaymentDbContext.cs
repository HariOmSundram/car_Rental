using Microsoft.EntityFrameworkCore;
using BookingPaymentAPI.Models;

namespace BookingPaymentAPI.Data
{
    public class BookingPaymentDbContext : DbContext
    {
        public BookingPaymentDbContext(DbContextOptions<BookingPaymentDbContext> options)
            : base(options)
        {
        }

        public DbSet<Booking> Bookings { get; set; }
        public DbSet<Payment> Payments { get; set; }

        public DbSet<ModeOfPayment> ModeOfPayment { get; set;}

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Booking>().ToTable("booking"); 
            modelBuilder.Entity<Payment>().ToTable("payment");
            modelBuilder.Entity<ModeOfPayment>().ToTable("mode_of_payment"); 
        }
    }
}
